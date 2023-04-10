package com.example.restapi.domain

import java.time.LocalDateTime

data class OrderDTO(
    val orderId: Long? = null,
    val name: String? = null,
    val orderDate: LocalDateTime? = null,
    val orderStatus: OrderStatus? = null,
    val address: Address? = null
) {
    constructor(order: Order) : this(
        orderId = order.id,
        name = order.member?.name,
        orderDate = order.orderDate,
        orderStatus = order.status,
        address = order.delivery?.address
    )
}