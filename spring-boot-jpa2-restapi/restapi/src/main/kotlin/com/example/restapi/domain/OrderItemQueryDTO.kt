package com.example.restapi.domain

class OrderItemQueryDTO(
    val orderId: Long? = null,
    var itemName: String? = null,
    var orderPrice: Int? = null,
    var count: Int? = null
)