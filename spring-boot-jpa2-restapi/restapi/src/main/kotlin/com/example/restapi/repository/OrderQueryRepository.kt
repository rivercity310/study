package com.example.restapi.repository

import com.example.restapi.domain.OrderItemQueryDTO
import com.example.restapi.domain.OrderQueryDTO
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class OrderQueryRepository(@PersistenceContext private val em: EntityManager) {
    private fun findOrders(): List<OrderQueryDTO> =
        em.createQuery("select new com.example.restapi.domain.OrderQueryDTO(o.id, m.name, o.orderDate, o.status, d.address)" +
                " from Order o" +
                " join o.member m" +
                " join o.delivery d", OrderQueryDTO::class.java)
                .resultList

    private fun findOrderItems(orderId: Long?): List<OrderItemQueryDTO> =
        em.createQuery("select new com.example.restapi.domain.OrderItemQueryDTO(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                " from OrderItem oi" +
                " join oi.item i" +
                " where oi.order.id = :orderId", OrderItemQueryDTO::class.java)
                .setParameter("orderId", orderId)
                .resultList

    internal fun findOrderQueryDTOs(): List<OrderQueryDTO> {
        val result: List<OrderQueryDTO> = findOrders()
        result.forEach { it.orderItems = findOrderItems(it.orderId) }
        return result
    }
}