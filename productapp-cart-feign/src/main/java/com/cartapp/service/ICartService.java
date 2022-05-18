package com.cartapp.service;

import com.cartapp.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// cart-service is the client for product-service
// give the name of the service to which you want to connect
// this is the proxy for productService
// the implementation class will be created during the runtime
@FeignClient(name = "PRODUCT-SERVICE")
public interface ICartService {

    @GetMapping("/product-api/products/category/{category}")
    List<Product> getByCategory(@PathVariable("category") String category);

    @GetMapping("/product-api/products/product-id/{productId}")
    Product getById(@PathVariable("productId") int productId);

    @GetMapping("/product-api/products/choice/{choice}")
    List<Product> getByChoice(@PathVariable("choice") String choice);

}
