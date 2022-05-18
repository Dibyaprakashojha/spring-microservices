package com.cartapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
// this is a microservice
public class ProductCartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCartServiceApplication.class, args);
	}

	// Spring creates the object of rest template
	@Bean
    @LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
