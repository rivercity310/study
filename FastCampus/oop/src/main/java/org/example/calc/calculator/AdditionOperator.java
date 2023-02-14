package org.example.calc.calculator;

public class AdditionOperator implements NewArithmeticOperator {
    @Override
    public boolean supports(String operator) {
        return "+".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber op1, PositiveNumber op2) {
        return op1.toInt() + op2.toInt();
    }
}
