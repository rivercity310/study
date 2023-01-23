package com.example.restapi.api

import com.example.restapi.domain.*
import com.example.restapi.repository.OrderRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

/** 연관 관계 xToOne -> 조회 성능 최적화
 * Order -> Member (N:1)
 * Order -> Delivery (1:1)
 */
@RestController
class OrderSimpleApiController(private val orderRepository: OrderRepository) {
    /* V1: 엔티티 직접 노출의 부작용
        -> 무한 루프 문제(JsonIgnore),
        -> 지연로딩 문제(Hibernate5Module)
    */
    @GetMapping("/api/v1/simple-orders")
    internal fun ordersV1(): List<Order> =
        orderRepository.findAllByString(OrderSearch())


    /* V2: Result로 감싸서 DTO 반환
    *   -> 필요한 정보만 Result로 감싸서 반환
    *   -> 쿼리가 너무 많이 나감..
    *       -> (N+1 문제 발생: 첫번째 쿼리 때문에 N개의 쿼리가 추가로 나감)
    *       -> Order 쿼리 1개의 결과로 N개의 결과를 얻음 --> 다시 회원 조회 N + 배송 조회 N개의 쿼리가 나감
    * */
    @GetMapping("/api/v2/simple-orders")
    internal fun ordersV2(): Result<List<SimpleOrderDTO>> {
        val orders: List<Order> = orderRepository.findAllByString(OrderSearch())
        return Result(
            count = orders.size,
            data = orders.map(::SimpleOrderDTO)
        )
    }

    data class Result<T>(val count: Int, val data: T)
    data class SimpleOrderDTO(
        val orderId: Long? = null,
        val name: String? = null,
        val orderDate: LocalDateTime? = null,
        val orderStatus: OrderStatus? = null,
        val address: Address? = null
    ) {
        constructor(order: Order) : this(
            orderId = order.id,
            name = order.member?.name,          // Lazy 초기화
            orderDate = order.orderDate,
            orderStatus = order.status,
            address = order.delivery?.address   // Lazy 초기화
        )
    }


    /* V3: FETCH JOIN -> V2의 성능을 최적화
    *       -> 기본적으로 LAZY로 세팅하고, 필요한 경우에만 FETCH JOIN으로 묶어서 가져온다 (쿼리 1번)
    * */
    @GetMapping("/api/v3/simple-orders")
    internal fun ordersV3(): Result<List<SimpleOrderDTO>> {
        val orders = orderRepository.findAllWithMemberDelivery()
        return Result(
            count = orders.size,
            data = orders.map(::SimpleOrderDTO)
        )
    }


    /* V4: JPQL로 엔티티를 DTO로 변환해서 받아옴
            -> V3보다 쿼리문이 짧아짐, 하지만 재사용성이 떨어짐 (DTO가 이미 정해져있기 때문)
            -> V3 쓰는 것 추천
    */
    @GetMapping("/api/v4/simple-orders")
    internal fun orderV3(): Result<List<OrderDTO>> {
        val orders = orderRepository.findOrderDTO()
        return Result(
            count = orders.size,
            data = orders
        )
    }
}