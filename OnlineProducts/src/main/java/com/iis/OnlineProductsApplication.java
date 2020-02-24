package com.iis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.iis.entities", "com.iis.controller"})
@EnableJpaRepositories(basePackages = {"com.iis.service"})
public class OnlineProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineProductsApplication.class, args);
	}
}
