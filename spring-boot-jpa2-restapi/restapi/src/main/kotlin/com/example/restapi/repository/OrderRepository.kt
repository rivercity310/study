package com.example.restapi.repository

import com.example.restapi.domain.Order
import com.example.restapi.domain.OrderSearch
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils

@Repository
class OrderRepository(private val em: EntityManager) {
    internal fun save(order: Order): Unit =
        em.persist(order)

    internal fun findOne(id: Long): Order =
        em.find(Order::class.java, id)

    internal fun findAllByString(orderSearch: OrderSearch): List<Order> {
        var jpql = "select o from Order o join o.member m"
        var isFirstCondition = true

        if (orderSearch.orderStatus != null) {
            if (isFirstCondition) {
                jpql += " where"
                isFirstCondition = false
            }
            else jpql += " and"

            jpql += " o.status = :status"
        }

        if (StringUtils.hasText(orderSearch.memberName)) {
            if (isFirstCondition) {
                jpql += " where"
                isFirstCondition = false
            }
            else jpql += " and"

            jpql += " m.name like :name"
        }

        var query = em.createQuery(jpql, Order::class.java)
            .setMaxResults(1000)

        if (orderSearch.orderStatus != null)
            query = query.setParameter("status", orderSearch.orderStatus)

        if (StringUtils.hasText(orderSearch.memberName))
            query = query.setParameter("name", orderSearch.memberName)

        return query.resultList
    }
}