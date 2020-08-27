package com.finology;

import com.finology.service.ProductCrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableCaching
public class FinologyWebCrawlerApplication implements ApplicationRunner {

	@Autowired
	private ProductCrawlService service;

	private final String URL = "https://magento-test.finology.com.my/index.php/";

	public static void main(String[] args) {
		SpringApplication.run(FinologyWebCrawlerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		service.crawlProduct(URL);
	}

	@Bean
	public CacheManager cacheManager(){
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		Cache cache = new ConcurrentMapCache("finologyCache");
		cacheManager.setCaches(Arrays.asList(cache));
		return cacheManager;
	}
}
