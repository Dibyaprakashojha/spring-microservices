package com.orderapp.controllers;

import com.orderapp.model.Order;
import com.orderapp.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-api")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService ;

    @GetMapping("/orders/view-orders")
    Order viewOrder() {
        return orderService.viewOrder();
    }
}
