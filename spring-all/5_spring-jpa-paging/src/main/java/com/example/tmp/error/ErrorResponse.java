package com.example.tmp.error;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public record ErrorResponse(String message, String code, Integer status, List<FieldError> errors) {
    @Builder
    public ErrorResponse(String message, String code, Integer status, List<FieldError> errors) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.errors = (errors == null) ? new ArrayList<>() : errors;
    }

    public record FieldError(String field, String value, String reason) {
        @Builder
        public FieldError(String field, String value, String reason) {
           this.field = field;
           this.value = value;
           this.reason = reason;
        }
    }
}
