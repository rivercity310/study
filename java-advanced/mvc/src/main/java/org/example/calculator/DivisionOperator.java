package org.example.calculator;

import java.util.Objects;

public class DivisionOperator implements NewArithmeticOperator {
    @Override
    public boolean supports(String operator) {
        return Objects.equals(operator, "/");
    }

    @Override
    public int calculate(PositiveNumber op1, PositiveNumber op2) {
        return op1.toInt() / op2.toInt();
    }
}
