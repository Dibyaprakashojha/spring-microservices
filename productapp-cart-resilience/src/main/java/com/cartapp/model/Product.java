package com.cartapp.model;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
	private Integer productId;
	private String name;
	private String brand;
	private String category;
	private double price;
}
