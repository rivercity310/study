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
            "select new com.example.restapi.domain.OrderDTO(o.id, m.name, o.orderDate, o.status, d.address) " +
                    " from Order o" +
            " join o.member m" +
            " join o.delivery d", OrderDTO::class.java)
            .resultList

    internal fun findAllWithItem(): List<Order> =
        /* distinct: SQL 기능: 중복제거 + JPA에서의 기능: Order의 PK가 같으면 중복된 ROW 제거 */
        /*      ---> 즉 엔티티가 중복인 경우 걸러서 컬렉션에 담아준다. DB에서 가져올 때는 걸러내지 못하고 4개를 가져옴 */
        em.createQuery(
            "select distinct o from Order o" +
            " join fetch o.member m" +
            " join fetch o.delivery d" +
            " join fetch o.orderItems oi" +
            " join fetch oi.item i", Order::class.java)
            .setFirstResult(1)
            .setMaxResults(100)
            .resultList

    internal fun findAllWithMemberDeliveryWithPaging(offset: Int, limit: Int): List<Order> =
        em.createQuery(
            "select o from Order o" +
            " join fetch o.member m" +
            " join fetch o.delivery d", Order::class.java)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .resultList
}