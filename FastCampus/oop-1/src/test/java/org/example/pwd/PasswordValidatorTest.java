package org.example.pwd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

/**
 * [ 요구사항 ]
 * - 비밀번호는 최소 8자 이상 12자 이하
 * - 비밀번호가 범위내에 없으면 IllegalArgumentException 예외 발생
 * - ** 경계조건에 대해 테스트 코드를 작성해야 한다 (8 or 12인 경우) **   <-- @ParameterizedTest, @ValueSource
 */
public class PasswordValidatorTest {
    @DisplayName("비밀번호가 최소 8자 이상 12자 이하면 예외가 발생하지 않는다")
    @Test
    void validatePasswordTest() {
        // 해당 함수를 호출했을 때 Exception이 발생하지 않으면 Test 통과
        assertThatCode(() -> PasswordValidator.validate("serverwizard"))
                .doesNotThrowAnyException();
    }

    @DisplayName("비밀번호가 8자 미만 또는 12자 초과하는 경우 IllgalArgu.. 예외가 터져야 한다")
    @ParameterizedTest
    @ValueSource(strings = { "abcdefg", "abcdefgabcdef" })
    void validatePasswordTest2(String value) {
        // 예외 발생시 예외가 IllegalArgumentException의 인스턴스이고, 메세지가 의도한대로 나오는지 검증
        assertThatCode(() -> PasswordValidator.validate(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호 자릿수 확인");
    }
}
