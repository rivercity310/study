package com.example.restapi.domain

data class OrderSearch(
    var memberName: String? = null,
    var orderStatus: OrderStatus? = null
)