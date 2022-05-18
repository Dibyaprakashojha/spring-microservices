package com.orderapp.service;

import com.orderapp.model.Cart;
import com.orderapp.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private RestTemplate restTemplate;

    private String BASEURL = "http://CART-SERVICE/cart-api/cart";

    Order order = new Order();
    @Override
    public Order viewOrder() {
        String url = BASEURL.concat("/show-cart/");
        ResponseEntity<Cart> responseEntity = restTemplate.getForEntity(url, Cart.class);
        Cart cart = responseEntity.getBody();
        order.setOrderId(1);
        order.setOrderTotal(cart.getTotalcost());
        order.setCart(cart);
        order.setCity("Bengaluru");
        order.setOrderDate(LocalDate.of(2022,5,20));
        order.setPaymentMode("COD");
        return order;
    }
}
