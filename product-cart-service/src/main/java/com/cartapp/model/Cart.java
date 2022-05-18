package com.cartapp.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    int cartId;
    List<Product> products;
    double totalcost;
}