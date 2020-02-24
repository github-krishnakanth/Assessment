package com.iis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iis.entities.Product;
import com.iis.pojo.Response;
import com.iis.service.ProductService;

@RestController
@RequestMapping(value = "/products")
@EnableAutoConfiguration
public class ProductsController {
	
	@Autowired
	private ProductService<Product, Integer> productService;
	
	private Logger logger = LoggerFactory.getLogger(ProductsController.class);
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.TEXT_XML_VALUE)
	public Response addProduct(@RequestBody Product product) {
		Response response;
		productService.save(product);
		response = new Response(true, "Product added successfully");
		logger.info("Product Added Successfully : "+response);
		
		return response;
	}
	
	@RequestMapping(value = "/search/{productName}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public Product getProduct(@PathVariable("productName") String productName) {
		return productService.findProductByProductName(productName);
	}
	
	@RequestMapping(value = "/edit/{productId}/{price}/{msrp}", method = RequestMethod.PUT)
	public int editProduct(@PathVariable("productId") int productId, @PathVariable("price") double price, @PathVariable("msrp") double msrp) {
		return productService.updateProduct(productId, price, msrp);
	}
	
	@RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
	public Response deleteProduct(@PathVariable("productId") int productId) {
		Response response;
		productService.deleteById(productId);
		response = new Response(true, "Product deleted successfully");
		logger.info("Product deleted successfully : "+response);
		
		return response;
	}
}
