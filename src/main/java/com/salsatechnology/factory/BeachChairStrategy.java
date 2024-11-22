package com.salsatechnology.factory;

import java.math.BigDecimal;

public class BeachChairStrategy implements ProductStrategy {

    private static final long PRODUCT_VALUE = 35L;
    private static final BigDecimal USER_VALUE = BigDecimal.valueOf(5);

    @Override
    public BigDecimal getUserValue() {
        return USER_VALUE;
    }

    @Override
    public Long getProductValue() {
        return PRODUCT_VALUE;
    }
}
