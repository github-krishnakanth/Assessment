package com.iis.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iis.entities.Product;

@Repository
public interface ProductDao<T, ID extends Serializable> extends JpaRepository<T, ID>{
	
	@Query("select * from Product p where p.productName = :productName")
	Product findProductByProductName(@Param("productName") String productName);
	
	/* Product findProductByProductName(String productName); 
	 * 
	 * We can call this method with out providing any explicit Query
	 * because spring data can understand and internally creates the query by reading
	 * method name.*/

	@Transactional
	@Modifying
	@Query("update Product p set p.price = :price, p.msrp = :msrp where p.productId = :productId")
	int updateProduct(@Param("productId") int productId, @Param("price") double price, @Param("msrp") double msrp);
}
