package com.iis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.iis.service"})
@EnableAutoConfiguration
public class OnlineProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineProductsApplication.class, args);
	}
}
