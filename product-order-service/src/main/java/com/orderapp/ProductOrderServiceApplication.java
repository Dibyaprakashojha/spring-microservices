package com.orderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class ProductOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductOrderServiceApplication.class, args);
	}

	@Bean
	// this helps to choose the microservice running in a particular port
	// that has performance, speed and fault tolerance
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
