package solutions.info.influx;

//import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;*/

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"solutions.info.influx.dao"})
//@EnableSwagger2 
public class ProductsDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsDbApplication.class, args);
	}
	
	
	/*
	 * @Bean public Docket swaggerConfiguration() { return new
	 * Docket(DocumentationType.SWAGGER_2) .select()
	 * .paths(PathSelectors.ant("/product/*"))
	 * .apis(RequestHandlerSelectors.basePackage("solutions.info.influx")) .build()
	 * .apiInfo(apiDetails()); }
	 * 
	 * private ApiInfo apiDetails() { return new ApiInfo( "Online Products API",
	 * "Real time application", "1.0", "Authonticated can use", new
	 * Contact("Krishnakanth",
	 * "https://www.linkedin.com/in/krishnakanth-narava-355070199/",
	 * "tech.krishnakanth@gmail.com"), "API License",
	 * "https://www.linkedin.com/in/krishnakanth-narava-355070199/",
	 * Collections.emptyList()); }
	 */
}
