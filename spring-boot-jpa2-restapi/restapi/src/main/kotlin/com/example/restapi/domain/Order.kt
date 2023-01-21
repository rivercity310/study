package com.example.restapi.domain

import jakarta.persistence.*
import java.lang.IllegalStateException
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member? = null,

    @OneToMany(mappedBy = "order")
    var orderItems: List<OrderItem> = arrayListOf(),

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery? = null,

    var orderDate: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    var status: OrderStatus? = null,
)
{
    /* 생성 메서드 */
    companion object {
        internal fun createOrder(member: Member, delivery: Delivery, vararg orderItems: OrderItem): Order {
            val order = Order(
                status = OrderStatus.ORDER,
                orderDate = LocalDateTime.now()
            )

            order.setDelivery(delivery)
            order.setMember(member)
            orderItems.forEach { order.addOrderItem(it) }

            return order
        }
    }

    /* 연관관계 편의 메서드 */
    internal fun setMember(member: Member): Unit {
        this.member = member
        member.orders += this
    }

    internal fun addOrderItem(orderItem: OrderItem): Unit {
        orderItems += orderItem
        orderItem.order = this
    }

    internal fun setDelivery(delivery: Delivery) {
        this.delivery = delivery
        delivery.order = this
    }

    /* 비즈니스 로직 */
    internal fun cancel() {
        if (delivery?.status == DeliveryStatus.COMP)
            throw IllegalStateException("이미 배송중")

        this.status = OrderStatus.CANCEL
        orderItems.forEach { it.cancel() }
    }

    /* 조회 로직 */
    internal fun getTotalPrice() =
        orderItems.map(OrderItem::getTotalPrice).sum()
}