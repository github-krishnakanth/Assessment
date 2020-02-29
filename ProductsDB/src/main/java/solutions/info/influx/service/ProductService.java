package solutions.info.influx.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import solutions.info.influx.dao.ProductRepository;
import solutions.info.influx.entities.Product;
import solutions.info.influx.exception.ProductException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	
	public Optional<Product> getProduct(int productId) {
		return repo.findById(productId);
	}
	
	public void addProduct(Product product) throws ProductException {
		repo.save(product);
	}
	
	public void editProduct(Product product) throws ProductException {
		repo.editProduct(product.getProductId(), product.getProductName(), product.getDescription(), product.getCost(), product.getExpiryMonth(), product.getExpiryYear());
	}
	
	public void deleteProduct(int productId) throws ProductException {
		repo.deleteById(productId);
	}
}
