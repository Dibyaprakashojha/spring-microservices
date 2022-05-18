package com.cartapp.service;

import com.cartapp.model.Cart;
import com.cartapp.model.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService{
    //  Make an API call to Product Service
    // Use an object of RestTemplate
    @Autowired
    private RestTemplate restTemplate;

    // port, url, responseEntity-> getForEntity, object-> getForObject
    // pass the service name
    private String BASEURL = "http://PRODUCT-SERVICE/product-api/products";

    Cart cart = new Cart();
    List<Product> productList = new ArrayList<>();
    int totalcost;

    //annotate the method for handling exceptions
    @CircuitBreaker(name = "CART-SERVICE",fallbackMethod = "fallBackFindByCategory")
    @Override
    public List<Product> getByCategory(String category) {
//        String url = BASEURL+"/category/"+category;
        String url = BASEURL.concat("/category/").concat(category);
        ResponseEntity<List> responseEntity =
                restTemplate.getForEntity(url,List.class);
        // response entity has header, status and body
        System.out.println(responseEntity.getStatusCode());
        List<Product> products = responseEntity.getBody();
        return products;
    }

    // fallback method -->
    // method is similar to the catch method for catching the exceptions thrown
    // has same method signature similar to the calling method
    // should have the exception thrown as last parameter
    public List<Product> fallBackFindByCategory(String category,RuntimeException e) {
        return new ArrayList<>(); // return an empty list instead of exception
    }

    @CircuitBreaker(name = "CART-SERVICE",fallbackMethod = "fallBackFindById")
    @Override
    public Product getById(Integer productId) {
        String url = BASEURL.concat("/product-id/")+productId;
        ResponseEntity<Product> productResponseEntity =
                restTemplate.getForEntity(url, Product.class);
        System.out.println(productResponseEntity.getStatusCode());
        Product product = productResponseEntity.getBody();
        return product;
    }

    public Product fallBackFindById(Integer productId, RuntimeException e) {
        return new Product();
    }

    @CircuitBreaker(name = "CART-SERVICE",fallbackMethod = "fallBackFindByChoice")
    @Override
    public List<Product> getByChoice(String choice) {
        String url = BASEURL.concat("/choice/").concat(choice);
        List<Product> products =
                restTemplate.getForObject(url,List.class);
        return products;
    }

    public List<Product> fallBackFindByChoice(String choice,RuntimeException e) {
        return new ArrayList<>();
    }

    @CircuitBreaker(name = "CART-SERVICE",fallbackMethod = "fallBackAddToCart")
    @Override
    public void addToCart(Integer productId) {
        // get the item by id
        String url = BASEURL.concat("/product-id/")+productId;
        ResponseEntity<Product> productResponseEntity = restTemplate.getForEntity(url, Product.class);
        Product product = productResponseEntity.getBody();
        double cost = product.getPrice();
        totalcost+=cost;
        productList.add(product);
        cart.setCartId(1);
        cart.setProducts(productList);
        cart.setTotalcost(totalcost);
    }

    public void fallBackAddToCart(Integer productId) {

    }

    @CircuitBreaker(name = "CART-SERVICE",fallbackMethod = "fallBackShowCart")
    @Override
    public Cart showCart() {
        return cart;
    }

    public Cart fallBackShowCart() {
        return new Cart();
    }
}
