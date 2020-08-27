package com.finology.service;

import com.finology.adapter.ProductAdapter;
import com.finology.entity.Product;
import com.finology.repository.ProductRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import static com.finology.bo.ProductBo.*;
import static com.finology.util.OutputColors.*;

@Service
public class ProductCrawlService {

    @Autowired
    private ProductRepository productRepository;

    private Queue<String> urlQueue = new PriorityQueue<String>();
    private Set<String> viewedSet = new HashSet<String>();

    @CachePut(value = "finologyCache")
    public void crawlProduct(String url) {
        urlQueue.add(url);
        System.out.println(CYAN + "Waiting queue size: " + urlQueue.size() + RESET);
        Document doc;

        while (!urlQueue.isEmpty()) {
            String currentUrl = urlQueue.poll();
            if (viewedSet.contains(currentUrl)) {
                checkWhetherCrawlIsFinishedOrNot();
                continue;
            }

            try {
                System.out.println("Fetching data from " + currentUrl);
                doc = Jsoup.connect(currentUrl).get();

                findAllLinksInCurrentUrlAndAddToQueue(doc);

                String content = doc.select("meta[property=og:type]").attr("content");
                if (!content.equals("product")) {
                    viewedSet.add(currentUrl);
                    System.out.println(YELLOW + "Url is added to ignore list." + RESET);
                    System.out.println(YELLOW + "Ignore list size: " + viewedSet.size() + RESET);
                    System.out.println(CYAN + "Waiting queue size: " + urlQueue.size() + RESET);
                    checkWhetherCrawlIsFinishedOrNot();
                    continue;
                }
                Map<String, String> productMap = getProduct(doc);
                Product product = ProductAdapter.convert(productMap);

                productRepository.save(product);
                System.out.println(GREEN + "One product is added to database." + RESET);

                viewedSet.add(currentUrl);

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(CYAN + "Waiting queue size: " + urlQueue.size() + RESET);
            checkWhetherCrawlIsFinishedOrNot();
        }
    }

    private void findAllLinksInCurrentUrlAndAddToQueue(Document doc){
        Elements links = doc.select("a[href]");
        System.out.println(BLUE + links.size() + " links found." + RESET);
        int i = 0;
        for(Element link : links){
            String linkStr = link.attr("abs:href");
            if( linkStr.startsWith("https://magento-test.finology.com.my/index.php/downloadable/download/sample/sample_id/") ||
                    linkStr.startsWith("mailto:") ||
                    linkStr.contains("https://magento-test.finology.com.my/index.php/privacy-policy/") ||
                    linkStr.contains("https://magento-test.finology.com.my/index.php/customer-service") ||
                    linkStr.contains("https://magento-test.finology.com.my/index.php/joust-duffle-bag.html")) {
                linkStr = "";
            }
            if (linkStr.contains("#")) {
                linkStr = linkStr.substring(0, linkStr.indexOf("#"));
            }
            if (linkStr.contains("?")) {
                linkStr = linkStr.substring(0, linkStr.indexOf("?"));
            }
            if (!urlQueue.contains(linkStr) && !viewedSet.contains(linkStr) && linkStr != "") {
                i++;
                urlQueue.add(linkStr);
            }
        }
        System.out.println(YELLOW + (links.size() - i) + " links are ignored." + RESET);
        System.out.println(BLUE + i + " links are added to queue." + RESET);
    }

    private void checkWhetherCrawlIsFinishedOrNot(){
        if(urlQueue.size() == 0){
            System.out.println(GREEN + "***CRAWLING IS FINISHED***" + RESET);
            System.out.println(PURPLE + "All the fetched information is saved in finology.db database" + RESET);
        }
    }

    private Map<String, String> getProduct(Document doc){
        Map<String, String> productMap = new HashMap<String, String>();

        getTitle(doc, productMap);
        getPrice(doc, productMap);
        getDescription(doc, productMap);
        getExtraInformation(doc, productMap);

        return productMap;
    }

    private void getTitle(Document doc, Map<String, String> productMap){
        String title = doc.title();
        productMap.put(TITLE, title);
    }

    private void getPrice(Document doc, Map<String, String> productMap){
        String price = doc.select("div.product-info-main")
                .select("div.product-info-price")
                .select("span.price")
                .text();
        productMap.put(PRICE, price);
    }

    private void getDescription(Document doc, Map<String, String> productMap){
        String description = doc.select("div.product.attribute.description")
                .text();
        productMap.put(DESCRIPTION, description);
    }

    private void getExtraInformation(Document doc, Map<String, String> productMap){
        StringBuilder extraInformation = new StringBuilder();
        String style = doc.getElementById("product-attribute-specs-table")
                .select("td[data-th=Style]")
                .text();
        String material = doc.getElementById("product-attribute-specs-table")
                .select("td[data-th=Material]")
                .text();
        String pattern = doc.getElementById("product-attribute-specs-table")
                .select("td[data-th=Pattern]")
                .text();
        String climate = doc.getElementById("product-attribute-specs-table")
                .select("td[data-th=Climate]")
                .text();

        extraInformation
                .append("Style ").append(style)
                .append("\nMaterial").append(material)
                .append("\nPattern").append(pattern)
                .append("\nClimate").append(climate);

        productMap.put(EXTRA_INFORMATION, extraInformation.toString());
    }
}
