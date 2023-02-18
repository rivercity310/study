package org.example.domain

class Menu(private val menuItems: List<MenuItem>) {
    fun choose(itemName: String): MenuItem =
        menuItems.find { it.name == itemName } ?:
            throw IllegalArgumentException("잘못된 메뉴 이름")



}