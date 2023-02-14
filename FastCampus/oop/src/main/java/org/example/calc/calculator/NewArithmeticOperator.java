package org.example.calc.calculator;

public interface NewArithmeticOperator {
    boolean supports(String operator);
    int calculate(PositiveNumber op1, PositiveNumber op2);
}
