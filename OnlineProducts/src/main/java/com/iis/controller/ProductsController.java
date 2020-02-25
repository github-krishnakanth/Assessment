package com.iis.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductsController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_XML_VALUE)
	public Response createProduct(@RequestBody Product product) {
		
		return productService.addProduct(product);
	}
	
	@RequestMapping(value = "/search/{productName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product viewProduct(@PathVariable("productName") String productName) {
		return productService.getProduct(productName);
	}
	
	@RequestMapping(value = "/edit/{productId}/{price}/{msrp}", method = RequestMethod.PUT)
	public int editProduct(@PathVariable("productId") int productId, @PathVariable("price") double price, @PathVariable("msrp") double msrp) {
		return productService.updateProduct(productId, price, msrp);
	}
	
	@RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response deleteProduct(@PathVariable("productId") int productId) {
		return productService.deleteProduct(productId);
	}
}
