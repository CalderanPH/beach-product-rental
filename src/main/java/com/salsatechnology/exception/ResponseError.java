package com.salsatechnology.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseError {

    private String message;

    public ResponseError(String errorMessage) {
        this.message = errorMessage;
    }
}
