package com.salsatechnology.dto;

import com.salsatechnology.model.ProductType;
import lombok.Data;

@Data
public class ProductOrderByUsernameDTO {

    private ProductType productType;
    private Integer timeHour;
    private Long productValue;
    private Long productTotal;
    private Long userAmount;
}
