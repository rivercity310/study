package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        // given
        Member member = createMember("멤버1", new Address("노원구", "길가", "11111"));
        Item book = createBook("시골 JPA", 10000, 10);

        // when
        Long orderId = orderService.order(member.getId(), book.getId(), 2);

        // then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확한가", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격 = 가격 * 수량", 10000 * 2, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 감소하는가", 8, book.getStockQuantity());
    }

    @Test
    public void 재고수량초과시_예외작동() throws Exception {
        // given
        Member member = createMember("멤버1", new Address("노원구", "길가", "11111"));
        Item book = createBook("시골 JPA", 10000, 10);
        int orderCount = 11;

        // when
        try { orderService.order(member.getId(), book.getId(), orderCount); }
        catch (NotEnoughStockException e) { return; }

        // then
        fail("재고 수량 부족 예외 발생 실패");
    }

    @Test
    public void 주문취소() throws Exception {
        // given
        Member member = createMember("멤버1", new Address("노원", "길", "11111"));
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        // when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        orderService.cancelOrder(orderId);

        // then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals("주문 취소시 상태는 CANCEL", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 다시 재고가 복구되어야 함", 10, item.getStockQuantity());
    }


    private Member createMember(String name, Address address) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(address);
        em.persist(member);

        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);

        return book;
    }
}