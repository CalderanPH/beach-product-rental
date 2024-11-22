package com.salsatechnology.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionConstants {

    public static final String PRODUCT_ORDER_NOT_FOUND_FOR_THIS_USERNAME = "No product order was found for the provided username.";
    public static final String PRODUCT_TYPE_NOT_FOUND = "The specified product type was not found.";

}
