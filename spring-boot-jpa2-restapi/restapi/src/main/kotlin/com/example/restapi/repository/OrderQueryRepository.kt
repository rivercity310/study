package com.example.restapi.repository

import com.example.restapi.domain.OrderFlatDTO
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

    internal fun findOrderQueryDTOs(): List<OrderQueryDTO> =
        findOrders().map {
            it.orderItems = findOrderItems(it.orderId)
            it
        }

    /* where가 아닌 in으로 한방에 가져오기 */
    internal fun findAllByDTO_optimization(): List<OrderQueryDTO> {
        val result: List<OrderQueryDTO> = findOrders()
        val orderIds: List<Long?> = result.map { it.orderId }

        val orderItemMap: Map<Long, List<OrderItemQueryDTO>> = em.createQuery(
            "select new com.example.restapi.domain.OrderItemQueryDTO(oi.order.id, i.name, oi.orderPrice, oi.count)" +
            " from OrderItem oi" +
            " join oi.item i" +
            " where oi.order.id in :orderIds", OrderItemQueryDTO::class.java)
            .setParameter("orderIds", orderIds)
            .resultList
            .groupBy { it.orderId!! }

        result.forEach { it.orderItems = orderItemMap[it.orderId!!]!! }

        return result
    }

    internal fun findAllByDTO_flat(): List<OrderFlatDTO> =
        em.createQuery(
            "select distinct new com.example.restapi.domain.OrderFlatDTO(o.id, m.name, o.orderDate, o.status, d.address, i.name, oi.orderPrice, oi.count)" +
            " from Order o" +
            " join o.member m" +
            " join o.delivery d" +
            " join o.orderItems oi " +
            " join oi.item i", OrderFlatDTO::class.java)
            .resultList
}