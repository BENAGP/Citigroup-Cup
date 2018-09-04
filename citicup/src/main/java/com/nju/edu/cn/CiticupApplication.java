package com.nju.edu.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CiticupApplication {

	public static void main(String[] args) {
		SpringApplication.run(CiticupApplication.class, args);
	}
}
