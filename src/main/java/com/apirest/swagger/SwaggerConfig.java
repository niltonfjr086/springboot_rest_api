package com.apirest.swagger;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

	/**
	 * Depends on the URL format > http://SERVER-ADDRESS:PORT/CONTEXT/swagger-ui.html
	 * @param request
	 * @return
	 */
	public static Boolean isSwaggerResource(HttpServletRequest request) {

		String[] reqArray = request.getRequestURL().toString().split("/");
		String pkgArgument = reqArray[4];

		if (reqArray[reqArray.length - 1].equals("swagger-ui.html") || pkgArgument.equals("webjars")
				|| pkgArgument.equals("swagger-resources") || pkgArgument.equals("v2")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.apirest.controller")).paths(PathSelectors.any()).build()
				.apiInfo(metaInfo());
	}

	@SuppressWarnings("rawtypes")
	private ApiInfo metaInfo() {

		ApiInfo apiInfo = new ApiInfo("REST API", "RESTFULL Middleware", "1.0", "Terms of Service",
				new Contact("Nilton Felipetto Jr.", "https://br.linkedin.com/in/nilton-junior-felipetto",
						"niltonfjnr@hotmail.com"),
				"Apache License Version 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList<VendorExtension>());

		return apiInfo;
	}

}