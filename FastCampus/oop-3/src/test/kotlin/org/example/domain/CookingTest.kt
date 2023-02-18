package org.example.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CookingTest {
    @DisplayName("메뉴에 해당하는 음식 만들기")
    @Test
    fun makeCookTest() {
        val cooking = Cooking()
        val menuItem = MenuItem("돈까스", 7000)
        val cook: Cook = cooking.makeCook(menuItem)

        assertThat(cook).isEqualTo(Cook("돈까스", 7000))
    }
}