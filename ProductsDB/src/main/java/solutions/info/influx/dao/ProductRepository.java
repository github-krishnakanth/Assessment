package solutions.info.influx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import solutions.info.influx.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Transactional
	@Modifying
	@Query("update Product set productName=:productName, description=:description, cost=:cost, expiryMonth=:expiryMonth, expiryYear=:expiryYear where productId=:productId")
	void editProduct(@Param("productId") int productId, @Param("productName") String productName, @Param("description") String description, @Param("cost") double cost, @Param("expiryMonth") String expiryMonth, @Param("expiryYear") String expiryYear);
}
