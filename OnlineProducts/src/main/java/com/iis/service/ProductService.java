package com.iis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iis.dao.ProductDao;
import com.iis.entities.Product;
import com.iis.pojo.Response;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao<Product, Integer> productDao;
	private Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	public Response addProduct(Product product) {
		Response response;
		productDao.save(product);
		response = new Response(true, "Product added successfully");
		logger.info("Product Added Successfully : "+response);
		
		return response;
	}
	
	public Product getProduct(String productName) {
		return productDao.findProductByProductName(productName);
	}
	
	public int updateProduct(int productId, double price, double msrp) {
		return productDao.updateProduct(productId, price, msrp);
	}
	
	public Response deleteProduct(int productId) {
		Response response;
		productDao.deleteById(productId);
		response = new Response(true, "Product deleted successfully");
		logger.info("Product deleted successfully : "+response);
		
		return response;
	}
}
