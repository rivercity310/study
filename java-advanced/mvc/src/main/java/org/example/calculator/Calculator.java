package org.example.calculator;


import java.util.List;

public class Calculator {
    private static final List<NewArithmeticOperator> arithmeticOperators =
            List.of(new AdditionOperator(),
                    new SubtractionOperator(),
                    new MultiplicationOperator(),
                    new DivisionOperator());

    public static int calculate(PositiveNumber operand, String op, PositiveNumber operand2) {
        return arithmeticOperators.stream()
                .filter(operator -> operator.supports(op))
                .map(operator -> operator.calculate(operand, operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다"));
    }
}
