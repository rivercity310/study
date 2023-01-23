package com.example.restapi.api

import com.example.restapi.domain.*
import com.example.restapi.repository.OrderRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

/**
 * xToMany 관계에서 컬렉션 조회 최적화하기
 */
@RestController
class OrderApiController2(private val orderRepository: OrderRepository) {
    data class Result<T>(val count: Int, val data: T)
    data class OrderDTO(private val order: Order) {
        var orderId: Long? = null
        var name: String? = null
        var orderDate: LocalDateTime? = null
        var orderStatus: OrderStatus? = null
        var address: Address? = null
        var orderItems: List<OrderItemDTO> = arrayListOf()

        init {
            this.orderId = order.id
            this.name = order.member?.name
            this.orderDate = order.orderDate
            this.orderStatus = order.status
            this.address = order.delivery?.address
            this.orderItems = order.orderItems.map { OrderItemDTO(it) }
        }
    }

    data class OrderItemDTO(private val orderItem: OrderItem) {
        var itemName: String? = null
        var orderPrice: Int? = null
        var count: Int? = null

        init {
            this.itemName = orderItem.item?.name
            this.orderPrice = orderItem.item?.price
            this.count = orderItem.count
        }
    }

    /* V1: 프록시 강제 초기화 후 DTO 변환
     *    -> 엔티티 직접 반환시 jackson 무한루프 터짐 (양방향 관계에서 JsonIgnore 필수)
     *    -> dto 안에 다시 OrderItem 엔티티가 존재하므로 그 엔티티도 DTO로 변환
     *    -> 최종 결과를 Result로 감싸서 반환
     *
     *    -x-> 쿼리가 너무 많이 나감.....  fetch join 적용해야함 (컬렉션일 때 fetch join은 더 고민해야할 점이 있다)
     * */
    @GetMapping("/api/v1/orders")
    internal fun ordersV1(): Result<List<OrderDTO>> {
        /* 프록시 강제 초기화 후 DTO 변환 */
        val orders: List<OrderDTO> = orderRepository.findAllByString(OrderSearch())
            .map { OrderDTO(it) }

        return Result(
            count = orders.size,
            data = orders
        )
    }
}