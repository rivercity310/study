package com.example.restapi.repository

import com.example.restapi.domain.Order
import com.example.restapi.domain.OrderDTO
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

    internal fun findAllWithMemberDelivery(): List<Order> =
        /* fetch join: LAZY 무시하고 프록시 아닌 진짜 MEMBER, DELIVERY 한방에 가져옴 */
        em.createQuery(
            "select o from Order o" +
            " join fetch o.member m" +
            " join fetch o.delivery d", Order::class.java)
            .resultList

    internal fun findOrderDTO(): List<OrderDTO> =
        /* NEW 명령어를 사용하여 엔티티를 DTO로 즉시 변환해서 가져옴 */
        em.createQuery(
            "select new com.example.restapi.domain.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                    " from Order o" +
            " join o.member m" +
            " join o.delivery d", OrderDTO::class.java)
            .resultList
}