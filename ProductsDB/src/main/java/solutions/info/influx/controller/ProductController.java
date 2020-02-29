package solutions.info.influx.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import solutions.info.influx.entities.Product;
import solutions.info.influx.exception.ProductException;
import solutions.info.influx.pojo.Response;
import solutions.info.influx.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@RequestMapping(value = "/show/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Product> showProduct(@PathVariable("productId") int productId) {
		return service.getProduct(productId);
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response createProduct(@RequestBody Product product) throws ProductException {
		Response response;
		try {
			service.addProduct(product);
			response = new Response(true, "Product Added Successfully..");
		}catch(ProductException e) {
			response = new Response(false, "Product not Added");
			throw new ProductException("Product Not Added..");
		}
		return response;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response updateProduct(@RequestBody Product product) throws ProductException {
		Response response;
		try {
			service.editProduct(product);
			response = new Response(true, "Product Updated Successfully..");
		}catch(ProductException e) {
			response = new Response(false, "Product not Updated..");
			throw new ProductException("Product Not Updated..");
		}
		return response;
	}
	
	@RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response deleteProduct(@PathVariable("productId") int productId) throws ProductException {
		Response response;
		try {
			service.deleteProduct(productId);
			response = new Response(true, "Product Deleted Successfully..");
		}catch(ProductException e) {
			response = new Response(false, "Product not Deleted..");
			throw new ProductException("Product Not Deleted..");
		}
		return response;
	}
}
