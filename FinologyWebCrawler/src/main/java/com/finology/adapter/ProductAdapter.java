package com.finology.adapter;

import com.finology.entity.Product;

import java.util.Map;

import static com.finology.bo.ProductBo.*;

public class ProductAdapter {

    public static Product convert(Map<String, String> productMap){

        Product product = new Product();
        product.setName(productMap.get(TITLE));
        product.setPrice(productMap.get(PRICE));
        product.setDescription(productMap.get(DESCRIPTION));
        product.setExtraInformation(productMap.get(EXTRA_INFORMATION));

        return product;
    }
}
