package com.example.project_ogini.model.entities;

import lombok.Data;

@Data
public class OrderDetailDto {
    Integer id;
    String Product_Name;
    Integer Product_Price_Out;
    Integer Quantity;
}
