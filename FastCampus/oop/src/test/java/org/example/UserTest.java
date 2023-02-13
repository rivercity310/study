package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// BDD 방식
class UserTest {

    /**
     * 컨트롤하지 못하는 RandomPasswordGenerator --> 테스트 진행 불가
     * 따라서 상위 인터페이스를 생성하고 항상 올바른 패스워드를 생성하는 클래스와 항상 틀린 패스워드를 생성하는 클래스를
     * 테스트 영역에 생성하고 테스트를 진행한다.
     */
    @DisplayName("패스워드를 초기화한다")
    @Test
    void passwordTest() {
        // given
        User user = new User();
        User user2 = new User();

        // when
        user.initPassword(new CorrectPasswordGenerator());
        user2.initPassword(() -> "123456789123");

        // then
        assertThat(user.getPassword()).isNotNull();
        assertThat(user2.getPassword()).isNotNull();
    }

    @DisplayName("패스워드가 요구사항에 부합하지 않아 초기화가 되지 않는다")
    @Test
    void passwordTest2() {
        // given
        User user = new User();
        User user2 = new User();

        // when
        user.initPassword(new WrongPasswordGenerator());
        user2.initPassword(() -> "12345");

        // then
        assertThat(user.getPassword()).isNull();
        assertThat(user.getPassword()).isNull();
    }
}