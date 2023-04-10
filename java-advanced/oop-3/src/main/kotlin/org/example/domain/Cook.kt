package org.example.domain

class Cook(
    val name: String,
    val price: Int
) {
    constructor(menuItem: MenuItem) : this(
        name = menuItem.name,
        price = menuItem.price
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cook

        if (name != other.name) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + price
        return result
    }
}
