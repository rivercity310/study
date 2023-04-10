package org.example.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CustomerTest {
    @DisplayName("메뉴 이름에 해당하는 요리를 주문")
    @Test
    fun orderTest() {
        val menuItems = listOf(
            MenuItem("군만두", 3000),
            MenuItem("짜장면", 4000),
            MenuItem("짬뽕", 4500)
        )

        val customer = Customer()
        val menu = Menu(menuItems)
        val cooking = Cooking()

        Assertions.assertThatCode { customer.order("짬뽕", menu, cooking) }
            .doesNotThrowAnyException()
    }

}