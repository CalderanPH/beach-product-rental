package com.salsatechnology.assembler;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.factory.ProductStrategy;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class ProductOrderCalculator {

    private final Long totalValue;
    private final Long userAmount;
    private final Long productValue;

    public ProductOrderCalculator(ProductOrderDTO productOrderDTO, ProductStrategy product) {
        this.productValue = calculateProductValue(product.getProductValue()).longValue();
        this.totalValue = calculateTotalValue(productOrderDTO.getTimeHour(), BigDecimal.valueOf(this.productValue)).longValue();
        this.userAmount = calculateUserAmount(productOrderDTO.getTimeHour(), BigDecimal.valueOf(this.productValue), product.getUserValue()).longValue();
    }

    private BigDecimal calculateProductValue(Long productValue) {
        return new BigDecimal(productValue).multiply(BigDecimal.valueOf(100));
    }

    private BigDecimal calculateTotalValue(Integer timeHour, BigDecimal productValue) {
        return productValue.multiply(new BigDecimal(timeHour));
    }

    private BigDecimal calculateUserAmount(Integer timeHour, BigDecimal productValue, BigDecimal userValue) {
        BigDecimal value = calculateTotalValue(timeHour, productValue);
        BigDecimal userValuePercentage = userValue.divide(BigDecimal.valueOf(100));
        return value.multiply(userValuePercentage).setScale(0, RoundingMode.HALF_UP);
    }
}

