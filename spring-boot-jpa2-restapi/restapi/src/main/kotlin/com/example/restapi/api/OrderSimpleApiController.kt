package com.example.restapi.api

import com.example.restapi.domain.Order
import com.example.restapi.domain.OrderSearch
import com.example.restapi.repository.OrderRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/** 연관 관계 xToOne -> 조회 성능 최적화
 * Order -> Member (N:1)
 * Order -> Delivery (1:1)
 */
@RestController
class OrderSimpleApiController(private val orderRepository: OrderRepository) {
    @GetMapping("/api/v1/simple-orders")
    internal fun ordersV1(): List<Order> {  /* 엔티티 직접 노출 */
        return orderRepository.findAllByString(OrderSearch())
    }
}