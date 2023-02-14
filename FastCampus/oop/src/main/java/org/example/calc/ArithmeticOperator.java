package org.example.calc;

import java.util.Arrays;

public enum ArithmeticOperator {
    ADD("+") {
        @Override
        protected int arithmeticCalculate(int op1, int op2) {
            return op1 + op2;
        }
    },

    SUB("-") {
        @Override
        protected int arithmeticCalculate(int op1, int op2) {
            return op1 - op2;
        }
    },

    MUL("*") {
        @Override
        protected int arithmeticCalculate(int op1, int op2) {
            return op1 * op2;
        }
    },

    DIV("/") {
        @Override
        protected int arithmeticCalculate(int op1, int op2) {
            return op1 / op2;
        }
    };

    private final String operator;

    ArithmeticOperator(String operator) {
        this.operator = operator;
    }

    protected abstract int arithmeticCalculate(final int op1, final int op2);

    public static int calculate(int op1, String op, int op2) {
        return Arrays.stream(values())
                .filter(v -> v.operator.equals(op))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다"))
                .arithmeticCalculate(op1, op2);
    }
}
