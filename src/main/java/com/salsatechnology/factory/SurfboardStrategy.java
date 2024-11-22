package com.salsatechnology.factory;

import java.math.BigDecimal;

public class SurfboardStrategy implements ProductStrategy {

    private static final long PRODUCT_VALUE = 50L;
    private static final BigDecimal USER_VALUE = BigDecimal.valueOf(15.6);

    @Override
    public Long getProductValue() {
        return PRODUCT_VALUE;
    }

    @Override
    public BigDecimal getUserValue() {
        return USER_VALUE;
    }
}
