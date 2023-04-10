package com.example.restapi.domain

import com.fasterxml.jackson.annotation.JsonIgnore

class OrderItemQueryDTO(
    @JsonIgnore
    val orderId: Long? = null,
    var itemName: String? = null,
    var orderPrice: Int? = null,
    var count: Int? = null
)