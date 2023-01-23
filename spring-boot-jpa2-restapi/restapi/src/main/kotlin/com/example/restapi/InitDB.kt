package com.example.restapi

import com.example.restapi.domain.*
import com.example.restapi.domain.item.Book
import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class InitDB(val initService: InitService) {
    @PostConstruct
    internal fun init() {
        initService.init1()
        initService.init2()
    }

    @Component
    @Transactional
    class InitService(@PersistenceContext val em: EntityManager) {

        internal fun init1() {
            val member = createMember("userA", "서울", "노원", "11111")
            em.persist(member)

            val book1 = Book.createBookOrder("JPA1 BOOK", 10000, 100)
            em.persist(book1)

            val book2 = Book.createBookOrder("JPA2 BOOK", 20000, 200)
            em.persist(book2)

            val orderItem1 = OrderItem.createOrderItem(book1, 20000, 2)
            val orderItem2 = OrderItem.createOrderItem(book2, 60000, 3)
            val order = Order.createOrder(member, createDelivery(member), orderItem1, orderItem2)
            em.persist(order)
        }

        internal fun init2() {
            val member = createMember("userB", "서울", "도봉", "22222")
            em.persist(member)

            val book1 = Book.createBookOrder("SPRING1 BOOK", 30000, 300)
            em.persist(book1)

            val book2 = Book.createBookOrder("SPRING2 BOOK", 40000, 400)
            em.persist(book2)

            val orderItem1 = OrderItem.createOrderItem(book1, 120000, 4)
            val orderItem2 = OrderItem.createOrderItem(book2, 200000, 5)
            val order = Order.createOrder(member, createDelivery(member), orderItem1, orderItem2)
            em.persist(order)

        }

        private fun createMember(name: String, city: String, street: String, zipcode: String): Member {
            return Member(
                name = name,
                address = Address(city, street, zipcode))
        }

        private fun createDelivery(member: Member): Delivery = Delivery(address = member.address)
    }
}