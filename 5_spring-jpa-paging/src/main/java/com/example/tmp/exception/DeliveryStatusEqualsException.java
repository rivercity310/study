package com.example.tmp.exception;

import com.example.tmp.domain.DeliveryStatus;

public class DeliveryStatusEqualsException extends RuntimeException {
    private DeliveryStatus status;

    public DeliveryStatusEqualsException(DeliveryStatus status) {
        super(status.name() + " It can not be changed to the same state");
        this.status = status;
    }
}
