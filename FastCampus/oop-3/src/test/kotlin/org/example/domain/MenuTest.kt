package org.example.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class MenuTest {
    @DisplayName("메뉴 이름에 해당하는 메뉴 반환")
    @Test
    fun chooseTest() {
        val menuItems = listOf(
            MenuItem("돈까스", 5000),
            MenuItem("냉면", 4000)
        )

        val menu = Menu(menuItems)
        val menuItem = menu.choose("돈까스")

        assertThat(menuItem).isEqualTo(MenuItem("돈까스", 5000))
    }

    @DisplayName("메뉴판에 없는 메뉴 주문 시 예외 발생")
    @Test
    fun chooseTest2() {
        val menuItems = listOf(
            MenuItem("제육볶음", 7000),
            MenuItem("라면", 3000)
        )

        val menu = Menu(menuItems)

        assertThatCode { menu.choose("뚝배기불고기") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("잘못된 메뉴 이름")
    }
}