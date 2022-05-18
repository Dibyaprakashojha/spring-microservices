package com.productapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// this is a microservice
@EnableEurekaClient
public class ProductappProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductappProductServiceApplication.class, args);
	}

}
