package com.example.restapi.domain

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
class OrderFlatDTO(
    val orderId: Long? = null,
    var name: String? = null,
    var orderDate: LocalDateTime? = null,
    var orderStatus: OrderStatus? = null,
    var address: Address? = null,
    var itemName: String? = null,
    var orderPrice: Int? = null,
    var count: Int? = null
)