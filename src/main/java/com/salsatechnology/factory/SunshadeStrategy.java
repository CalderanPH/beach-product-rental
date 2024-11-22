package com.salsatechnology.factory;

import java.math.BigDecimal;

public class SunshadeStrategy implements ProductStrategy {

    private static final long PRODUCT_VALUE = 40L;
    private static final BigDecimal USER_VALUE = BigDecimal.valueOf(10.3);

    @Override
    public Long getProductValue() {
        return PRODUCT_VALUE;
    }

    @Override
    public BigDecimal getUserValue() {
        return USER_VALUE;
    }
}
