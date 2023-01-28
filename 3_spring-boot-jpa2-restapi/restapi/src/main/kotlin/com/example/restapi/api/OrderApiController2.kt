package com.example.restapi.api

import com.example.restapi.domain.*
import com.example.restapi.repository.OrderQueryRepository
import com.example.restapi.repository.OrderRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

/**
 * 1대다(xToMany) 관계에서 컬렉션 조회 최적화하기
 */
@RestController
class OrderApiController2(private val orderRepository: OrderRepository, private val orderQueryRepository: OrderQueryRepository) {
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

    /* V2: fetch join과 distinct를 통해 중복을 제거하고 한번에 가져오기
     *      -> join시 Order가 뻥튀기되는 중복 문제(1대다 조인) 해결
     *      -> 쿼리 1번으로 축소
     *      -x-> 1대다를 fetch join하는 경우 페이징이 애플리케이션에서 처리된다는 치명적인 단점이 있다. (OutOfMemory 위험성)
     *      -x-> DB에서 중복을 거르지 못하고 전체를 가져오기 때문에 데이터 전송량이 많다
    */
    @GetMapping("/api/v2/orders")
    internal fun ordersV2(): Result<List<OrderDTO>> {
        val orders: List<OrderDTO> = orderRepository.findAllWithItem()
            .map { OrderDTO(it) }

        return Result(
            count = orders.size,
            data = orders
        )
    }

    /* V3: BatchSize를 통해 페이징 + 컬렉션 엔티티 조회
     *      -> XToOne : 모두 fetch join (ToOne 관계는 row 수를 증가시키지 않음 -> 페이징 쿼리에 영향을 주지 않음)
     *      -> 컬렉션 : 지연 로딩으로 조회, 성능 최적화를 위해 hibernate.default_batch_fetch_size(글로벌 설정), BatchSize(개별 최적화) 적용
     *                  -> 위 옵션을 사용하면 컬렉션이나, 프록시 객체를 한꺼번에 설정한 size만큼 IN 쿼리로 조회
     *      -> DB에서 가져올 때부터 중복 없이 가져옴 (ToOne만 fetch join 했고, 테이블 단위로 IN 쿼리가 나가기 때문)
     */
    @GetMapping("/api/v3/orders")
    internal fun ordersV3(
        @RequestParam(value = "offset", defaultValue = "0") offset: Int,
        @RequestParam(value = "limit", defaultValue = "100") limit: Int): Result<List<OrderDTO>>
    {
        val orders: List<OrderDTO> = orderRepository.findAllWithMemberDeliveryWithPaging(offset, limit)
            .map { OrderDTO(it) }

        return Result(
            count = orders.size,
            data = orders
        )
    }

    /* V4: DTO 직접 조회
     *      -> 1:N 문제 발생 -> 개선해야됨
     */
    @GetMapping("/api/v4/orders")
    internal fun ordersV4(): Result<List<OrderQueryDTO>> {
        val data = orderQueryRepository.findOrderQueryDTOs()
        return Result(count = data.size, data = data)
    }

    /* V5: DTO 직접 조회2 : 쿼리 2번
     *      -> In을 이용하여 toMany 관계 쿼리 최적화
     *      -> Map을 사용해서 매칭 성능 향상 O(1)
     */
    @GetMapping("/api/v5/orders")
    internal fun orderV5(): Result<List<OrderQueryDTO>> {
        val data = orderQueryRepository.findAllByDTO_optimization()
        return Result(count = data.size, data = data)
    }

    /* v6: DTO 직접 조회3 : 한방 쿼리 (모두 JOIN)
     *      -> 쿼리 1번이지만 직접 중복 제거를 해주어야 함 (구현 안한 상태)
     * */
    @GetMapping("/api/v6/orders")
    internal fun orderV6(): Result<List<OrderFlatDTO>> {
        val data = orderQueryRepository.findAllByDTO_flat()
        return Result(count = data.size, data = data)
    }
}