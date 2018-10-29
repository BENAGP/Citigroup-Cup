package com.nju.edu.cn;

import com.nju.edu.cn.configuration.AuthorizeFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CiticupApplication {

	public static void main(String[] args) {
		SpringApplication.run(CiticupApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean testFilterRegistration() {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new AuthorizeFilter());
		registration.addUrlPatterns("/api/account/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("authorizeFilter");
		registration.setOrder(1);
		return registration;
	}
}
