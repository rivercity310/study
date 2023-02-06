package com.example.dsl;

import com.example.dsl.entity.Member;
import com.example.dsl.entity.Team;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.dsl.entity.QMember.member;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class QueryDslBasicTest {
    @PersistenceContext
    EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach    /* 각 테스트 실행 전 동작 */
    public void before() {
        System.out.println("------------------------------------------------------------");
        queryFactory = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        System.out.println("------------------------------------------------------------");
    }

    @Test
    public void startJPQL() {
        // member1 찾기
        Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(result.getUsername()).isEqualTo("member1");
        assertThat(result.getAge()).isEqualTo(10);

        System.out.println(result.getTeam());
        System.out.println("================================================");

        assertThat(result.getTeam().getName()).isEqualTo("teamA");
    }

    @Test
    public void startQueryDsl() {
        Member result = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        assert result != null;
        assertThat(result.getUsername()).isEqualTo("member1");
        assertThat(result.getAge()).isEqualTo(10);

        System.out.println(result.getTeam());
        System.out.println("================================================");
        assertThat(result.getTeam().getName()).isEqualTo("teamA");
    }

    @Test
    public void searchQuery() {
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1")
                        .or(member.age.between(10, 30)))
                .fetch();

        for (Member member : result) {
            System.out.println("member.getUsername() = " + member.getUsername());
            System.out.println("member.getAge() = " + member.getAge());
            System.out.println("---> member.getTeam().getName() = " + member.getTeam().getName());
        }
    }

    @Test
    public void searchAndParamTest() {
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(
                        member.team.name.eq("teamA"),       // and 조건은 쉼표로도 가능
                        member.age.between(10, 30)
                )
                .fetch();

        for (Member member : result) {
            System.out.println("member.getUsername() = " + member.getUsername());
            System.out.println("member.getAge() = " + member.getAge());
            System.out.println("---> member.getTeam().getName() = " + member.getTeam().getName());
        }
    }

    @Test
    public void resultFetchTest() {
        /* 멤버 목록 List 조회 */
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .fetch();

        /* 단건 조회 (null or NonUniqueResultException 가능성 존재) */
        Member fetchOne = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member3"))
                .fetchOne();

        /* 처음 1건만 조회 */
        Member fetchFirst = queryFactory
                .selectFrom(member)
                .fetchFirst();      // == limit(1).fetch()

        /* [deprecated] 페이징 처리시 사용 (totalCount 쿼리도 나감, 총 2번) */
        QueryResults<Member> results = queryFactory
                .selectFrom(member)
                .fetchResults();

        long total = results.getTotal();
        List<Member> content = results.getResults();

        /* [deprecated] 카운트 결과 가져옴 */
        long fetchCount = queryFactory
                .selectFrom(member)
                .fetchCount();
    }

    @Test
    public void sortTest() {
        /** [ 정렬 조건 ]
         * 1. 나이 내림차순
         * 2. 회원 이름 오름차순
         * 단, 2에서 회원 이름이 없으면 마지막에 출력 (null last)
         */

        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 80));
        em.persist(new Member("member6", 110));

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.goe(80))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();

        for (Member member : result) {
            System.out.println("member = " + member);
        }
    }
}
