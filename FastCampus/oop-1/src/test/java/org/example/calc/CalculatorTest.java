package org.example.calc;

import org.example.calc.calculator.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

public class CalculatorTest {
    @DisplayName("사칙 연산을 정상적으로 수행하는가")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void CalcTest(int op1, String op, int op2, int result) {
        int x = Calculator.calculate(new PositiveNumber(op1), op, new PositiveNumber(op2));
        assertThat(x).isEqualTo(result);
    }

    private static Stream<Arguments> formulaAndResult() {
        return Stream.of(
                arguments(1, "+", 2, 3),
                arguments(1, "-", 2, -1),
                arguments(4, "*", 2, 8),
                arguments(4, "/", 2, 2)
        );
    }

    // PositiveNumber 객체 생성 -> 나누기 0에 대한 예외는 더이상 발생하지 않음
    @DisplayName("0 또는 음수로 PositiveNumber 객체 생성시 예외 발생")
    @Test
    void calcExceptionTest() {
        assertThatCode(() -> Calculator.calculate(new PositiveNumber(10), "/", new PositiveNumber(0)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("PositiveNumber 객체 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = { 0, -1 })
    void createTest(int value) {
        assertThatCode(() -> new PositiveNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0 또는 음수를 전달할 수 없습니다");
    }
}
