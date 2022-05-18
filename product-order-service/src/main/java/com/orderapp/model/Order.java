package com.orderapp.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private Integer orderId;
    private LocalDate orderDate;
    private Cart cart;
    private String city;
    private String paymentMode;
    private double orderTotal;
}
