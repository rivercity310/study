package com.example.tmp.exception;

import com.example.tmp.error.ErrorCode;
import lombok.Getter;

@Getter
public class DeliveryNotFoundException extends RuntimeException {
    private Long id;
    private ErrorCode errorCode;

    public DeliveryNotFoundException(Long id) {
        super(id + " is not found");
        this.id = id;
        this.errorCode = ErrorCode.DELIVERY_NOT_FOUND;
    }
}
