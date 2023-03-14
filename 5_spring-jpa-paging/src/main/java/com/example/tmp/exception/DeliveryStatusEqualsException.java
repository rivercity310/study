package com.example.tmp.exception;

import com.example.tmp.domain.DeliveryStatus;
import com.example.tmp.error.ErrorCode;
import lombok.Getter;

@Getter
public class DeliveryStatusEqualsException extends RuntimeException {
    private final DeliveryStatus status;
    private final ErrorCode errorCode;

    public DeliveryStatusEqualsException(DeliveryStatus status) {
        super(status.name() + " It can not be changed to the same state");
        this.status = status;
        this.errorCode = ErrorCode.DELIVERY_STATUS_EQUALS;
    }
}
