package com.example.tmp.exception;

import lombok.Getter;

@Getter
public class DeliveryNotFoundException extends RuntimeException {
    private Long id;

    public DeliveryNotFoundException(Long id) {
        super(id + " is not found");
        this.id = id;
    }
}
