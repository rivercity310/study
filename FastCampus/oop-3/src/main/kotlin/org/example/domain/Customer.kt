package org.example.domain

class Customer {
    fun order(menuName: String, menu: Menu, cooking: Cooking) {
        val menuItem = menu.choose(menuName)
        val cook = cooking.makeCook(menuItem)
    }

}
