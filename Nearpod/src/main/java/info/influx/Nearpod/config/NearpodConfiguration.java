package info.influx.Nearpod.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableJpaRepositories(basePackages = "info.influx.Nearpod.repository")
@EnableTransactionManagement
@EntityScan(basePackages = "info.influx.Nearpod.model")
@EnableSwagger2
public class NearpodConfiguration {
	public Docket NearpodApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfoBuilder()
						.title("Nearpod Campaign Api")
						.description("Provides API's for the Login and Campaign Page.")
						.version("0.1.0")
						.build())
				.select()
				.paths(PathSelectors.any())
				.build();
	}
}
