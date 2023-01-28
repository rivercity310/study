package com.example.restapi.domain

import java.time.LocalDateTime

class OrderQueryDTO(
    val orderId: Long? = null,
    var name: String? = null,
    var orderDate: LocalDateTime? = null,
    var orderStatus: OrderStatus? = null,
    var address: Address? = null,
    var orderItems: List<OrderItemQueryDTO> = arrayListOf()
) {
    constructor(orderId: Long, name: String, orderDateTime: LocalDateTime, orderStatus: OrderStatus, address: Address) : this(
        orderId = orderId,
        name = name,
        orderDate = orderDateTime,
        orderStatus = orderStatus,
        address = address
    )
}