package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductappCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductappCloudGatewayApplication.class, args);
	}


	//create our own routes
//	@Bean
//	public RouteLocator customRoutes(RouteLocatorBuilder builder) {
//		return builder.routes()
//				// create individual routes with id,uri, predicate(path),filters
//				// for productService
//				.route("productService", ps -> ps.path("/product-api/**").uri("lb://PRODUCT-SERVICE"))
//				// for cartService
//				.route("cartService", ps -> ps.path("/cart-api/**").uri("lb://CART-SERVICE"))
//				//for orderService
//				.route("orderService", ps -> ps.path("/order-api/**").uri("lb://ORDER-SERVICE"))
//				.build();
//	}
}
