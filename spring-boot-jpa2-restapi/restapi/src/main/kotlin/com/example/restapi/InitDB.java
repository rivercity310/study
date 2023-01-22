package com.example.restapi;

import com.example.restapi.domain.*;
import com.example.restapi.domain.item.Book;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        System.out.println("init 메서드 실행");
        initService.init1();
        initService.init2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void init1() {
            Member member = createMember("userA", new Address("서울", "노원", "11111"));
            em.persist(member);

            Book book = createBook("JPA1", 10000, 100);
            em.persist(book);

            Book book2 = createBook("JPA2", 20000, 200);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.Companion.createOrderItem$restapi(book, 10000, 1);
            OrderItem orderItem2 = OrderItem.Companion.createOrderItem$restapi(book2, 20000, 2);

            Delivery delivery = createDelivery(member.getAddress());

            Order order = Order.Companion.createOrder$restapi(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void init2() {
            Member member = createMember("userB", new Address("서울", "도봉", "22222"));
            em.persist(member);

            Book book = createBook("Spring1 book", 30000, 300);
            em.persist(book);

            Book book2 = createBook("Spring2 book", 40000, 400);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.Companion.createOrderItem$restapi(book, 30000, 3);
            OrderItem orderItem2 = OrderItem.Companion.createOrderItem$restapi(book2, 40000, 4);

            Delivery delivery = createDelivery(member.getAddress());

            Order order = Order.Companion.createOrder$restapi(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private Member createMember(String name, Address address) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(address);

            return member;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);

            return book;
        }

        private Delivery createDelivery(Address address) {
            Delivery delivery = new Delivery();
            delivery.setAddress(address);
            return delivery;
        }
    }
}
