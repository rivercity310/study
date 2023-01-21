package com.example.restapi.domain.item

import com.example.restapi.exception.NotEnoughStockException
import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Item(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    open var id: Long? = null,

    open var name: String? = null,
    open var price: Int? = null,
    open var stockQuantity: Int? = null
)
{
    /* Business Logic */
    internal fun addStock(quantity: Int): Unit {
        this.stockQuantity = stockQuantity!! + quantity
    }

    internal fun removeStock(quantity: Int): Unit {
        val restStock = this.stockQuantity!! - quantity
        if (restStock < 0)
            throw NotEnoughStockException("need more stock")

        this.stockQuantity = restStock
    }
}