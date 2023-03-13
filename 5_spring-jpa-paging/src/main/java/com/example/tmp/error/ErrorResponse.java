package com.example.tmp.error;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {
    private final String message;
    private final String code;
    private final Integer status;
    private final List<FieldError> errors;

    @Builder
    public ErrorResponse(String message, String code, Integer status, List<FieldError> errors) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.errors = (errors == null) ? new ArrayList<>() : errors;
    }

    @Getter
    public static class FieldError {
        private final String field;
        private final String value;
        private final String reason;

        @Builder
        public FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }
    }
}
