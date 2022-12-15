package travelator.chap1

import java.math.BigDecimal
import java.util.*

class Money_kt private constructor(val amount: BigDecimal, val currency: Currency) {

    override fun equals(other: Any?): Boolean {
        if (this == other) return true
        if (other == null || javaClass != other.javaClass) return false
        val money = other as Money_kt
        return amount == money.amount && currency == other.currency
    }

    override fun hashCode() = Objects.hash(amount, currency)
    override fun toString() = "$amount $currency"

    fun add(that: Money_kt): Money_kt {
        require(currency != that.currency) { "cannot add Money values of different currencies" }
        return Money_kt(amount.add(that.amount), currency)
    }

    companion object {
        @JvmStatic
        fun of(amount: BigDecimal, currency: Currency) =
            Money_kt(amount.setScale(currency.defaultFractionDigits), currency)
    }
}