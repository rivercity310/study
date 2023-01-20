package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class itemUpdateTest {
    @PersistenceContext
    EntityManager em;

    @Test
    public void updateTest() throws Exception {
        Book book = em.find(Book.class, 1L);

        book.setName("123123");

        // 더티 체킹(변경 감지) -> 트랜잭션 내에서 변경사항이 있으면 커밋 시점에 JPA가 자동으로 update 쿼리를 날려줌

    }
}
