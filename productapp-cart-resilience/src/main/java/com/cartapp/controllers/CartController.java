package com.cartapp.controllers;

import com.cartapp.model.Cart;
import com.cartapp.model.Product;
import com.cartapp.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart-api")
public class CartController {

    @Autowired
    CartServiceImpl cartService;

    @GetMapping("cart/category/{category}")
    List<Product> getByCategory(@PathVariable("category") String category){
        return cartService.getByCategory(category);
    }

    @GetMapping("cart/product-id/{productId}")
    ResponseEntity<Product> getById(@PathVariable("productId") int productId){
        Product product = cartService.getById(productId);
        return  ResponseEntity.ok().body(product);
    }

    @GetMapping("cart/choice/{choice}")
    List<Product> getByChoice(@PathVariable("choice") String choice) {
        return cartService.getByChoice(choice);
    }

    @GetMapping("cart/add-to-cart/{productId}")
    ResponseEntity<String> addToCart(@PathVariable("productId") Integer productId){
        cartService.addToCart(productId);
        return  ResponseEntity.ok("Added to the cart");
    }

    @GetMapping("/cart/show-cart")
    ResponseEntity<Cart> showCart(){
        Cart cart = cartService.showCart();
        return  ResponseEntity.ok(cart);
    }
}
