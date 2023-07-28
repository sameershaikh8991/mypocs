package com.crud.Responses;


import lombok.Builder;

@Builder
public class ProductResposeDto {
    private String id;
    private String name;
    private  int qty;
    private  double price;

}
