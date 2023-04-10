package com.example.tmp.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    ACCOUNT_NOT_FOUND("AC_001", "해당 회원을 찾을 수 없습니다.", 404),
    EMAIL_DUPLICATION("AC_001", "이메일이 중복되었습니다.", 400),
    INPUT_VALUE_INVALID("???", "입력값이 올바르지 않습니다.", 400),
    PASSWORD_FAILED_EXCEEDED("???", "비밀번호 실패 횟수가 초과되었습니다.", 400),
    DELIVERY_NOT_FOUND("???", "해당하는 주문이 없습니다.", 400),
    DELIVERY_STATUS_EQUALS("???", "중복된 상태코드입니다.", 400);

    private final String code;
    private final String message;
    private final Integer status;

    ErrorCode(String code, String message, Integer status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
