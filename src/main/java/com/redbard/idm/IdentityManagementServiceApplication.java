package com.redbard.idm;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

@EnableWebMvc
@SpringBootApplication
public class IdentityManagementServiceApplication {

	public static final String AUTHORIZATION = "Authorization";
	public static final String HEADER = "Header";
	
	public static void main(String[] args) {
		SpringApplication.run(IdentityManagementServiceApplication.class, args);
	}

	@Bean
	public SecurityConfiguration securityConfiguration() {
		return SecurityConfigurationBuilder.builder().enableCsrfSupport(true).build();
	}

	@Bean
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().paths(paths()) // and by paths
				.build().securitySchemes(Collections.singletonList(apiKey()))
				.securityContexts(Collections.singletonList(securityContext()));
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(paths()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference(AUTHORIZATION, authorizationScopes));
	}

	private Predicate<String> paths() {
		return regex("/api/users.*");
	}

	private ApiKey apiKey() {
		return new ApiKey(AUTHORIZATION, AUTHORIZATION, HEADER);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Identity Management API")
				.description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum "
						+ "has been the industry's standard dummy text ever since the 1500s, when an unknown printer "
						+ "took a "
						+ "galley of type and scrambled it to make a type specimen book. It has survived not only five "
						+ "centuries, but also the leap into electronic typesetting, remaining essentially unchanged. "
						+ "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum "
						+ "passages, and more recently with desktop publishing software like Aldus PageMaker including "
						+ "versions of Lorem Ipsum.")
				.termsOfServiceUrl("http://redbard.com").license("Apache License Version 2.0")
				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE").version("1.0").build();
	}

}
