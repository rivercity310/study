package org.example.domain


class Cooking {
    fun makeCook(menuItem: MenuItem): Cook {
        return Cook(menuItem)
    }

}
