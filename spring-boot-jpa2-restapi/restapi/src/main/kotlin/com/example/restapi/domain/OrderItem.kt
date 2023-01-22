package com.example.restapi.domain

import com.example.restapi.domain.item.Item
import jakarta.persistence.*

@Entity
class OrderItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item? = null,

    var orderPrice: Int? = null,
    var count: Int? = null
)
{
    /* 생성 메서드 */
    companion object {
        internal fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
            val orderItem = OrderItem()

            orderItem.item = item
            orderItem.orderPrice = orderPrice
            orderItem.count = count

            item.removeStock(count)
            return orderItem
        }
    }

    /* 비즈니스 로직 */
    internal fun cancel(): Unit =
        item!!.addStock(count!!)

    internal fun getTotalPrice(): Int =
        orderPrice!! * count!!
}