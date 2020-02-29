package solutions.info.influx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"solutions.info.influx.dao"})
public class ProductsDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsDbApplication.class, args);
	}

}
