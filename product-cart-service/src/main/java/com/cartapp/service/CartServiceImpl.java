package com.cartapp.service;

import com.cartapp.model.Cart;
import com.cartapp.model.Product;
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

    @Override
    public Product getById(Integer productId) {
        String url = BASEURL.concat("/product-id/")+productId;
        ResponseEntity<Product> productResponseEntity =
                restTemplate.getForEntity(url, Product.class);
        System.out.println(productResponseEntity.getStatusCode());
        Product product = productResponseEntity.getBody();
        return product;

    }

    @Override
    public List<Product> getByChoice(String choice) {
        String url = BASEURL.concat("/choice/").concat(choice);
        List<Product> products =
                restTemplate.getForObject(url,List.class);
        return products;
    }

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

    @Override
    public Cart showCart() {
        return cart;
    }
}
