package org.example

import org.assertj.core.api.Assertions
import org.example.domain.MenuItem
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MenuItemTest {
    @DisplayName("메뉴 생성")
    @Test
    fun createTest() {
        Assertions.assertThatCode { MenuItem("만두", 5000) }
            .doesNotThrowAnyException()
    }
}