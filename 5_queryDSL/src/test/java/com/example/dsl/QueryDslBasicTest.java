package com.example.dsl;

import com.example.dsl.entity.Member;
import com.example.dsl.entity.QMember;
import com.example.dsl.entity.Team;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.dsl.entity.QMember.member;
import static com.example.dsl.entity.QTeam.*;
import static com.querydsl.jpa.JPAExpressions.*;
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

    @Test
    public void paging1() {
        List<Member> result = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetch();

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void paging2() {
        QueryResults<Member> result = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetchResults();

        assertThat(result.getTotal()).isEqualTo(4);
        assertThat(result.getLimit()).isEqualTo(2);
        assertThat(result.getOffset()).isEqualTo(1);
        assertThat(result.getResults().size()).isEqualTo(2);
    }

    @Test
    public void aggregation() {
        List<Tuple> result = queryFactory
                .select(
                        member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min()
                )
                .from(member)
                .fetch();

        Tuple tuple = result.get(0);

        assertThat(tuple.get(member.count())).isEqualTo(4);
        assertThat(tuple.get(member.age.sum())).isEqualTo(100);
        assertThat(tuple.get(member.age.avg())).isEqualTo(25);
        assertThat(tuple.get(member.age.max())).isEqualTo(40);
        assertThat(tuple.get(member.age.min())).isEqualTo(10);
    }

    /**
     * 팀의 이름과 각 팀의 평균 연령 구하기
     */
    @Test
    public void group() {
        List<Tuple> result = queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();

        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);

        assertThat(teamA.get(team.name)).isEqualTo("teamA");
        assertThat(teamA.get(member.age.avg())).isEqualTo(15);
        assertThat(teamB.get(team.name)).isEqualTo("teamB");
        assertThat(teamB.get(member.age.avg())).isEqualTo(35);
    }

    /**
     * 팀A에 소속된 모든 회원 찾기
     */
    @Test
    public void join() {
        List<Member> result = queryFactory
                .selectFrom(member)
                .leftJoin(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("member1", "member2");
    }

    @Test
    public void thetaJoin() {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        /*
                SELECT m FROM Member m, Team t
                WHERE m.username = t.name
         */

        List<Member> result = queryFactory
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("teamA", "teamB");

        for (Member member : result)
            System.out.println("member = " + member);
    }

    /**
     * [ join - on 절 ]
     * 1. join 대상을 필터링 또는, 연관관계가 없는 엔티티를 외부 조인 가능케 해줌
     *
     * ----> 예시 : 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
     * JPQL: SELECT m, t FROM Member m left join m.team t on t.name = 'teamA'
     */
    @Test
    public void join_on_filtering() {
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

        /**
         * on 절을 활용해 조인 대상을 필터링할 때
         * 1. 내부 조인: on절과 where절 동일 기능    ->     where 사용 권장
         * 2. 외부 조인: on절 사용                 ->     .on(team.name.eq("teamA")
         */

        for (Tuple tuple : result)
            System.out.println("tuple = " + tuple);
    }

    /**
     * [ 연관관계가 없는 엔티티 외부조인 ]
     * 조건: 회원의 이름이 팀 이름과 같은 대상 외부 조인
     */
    @Test
    public void join_on_no_relation() {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team)
                .on(member.username.eq(team.name))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void fetchJoinNo() {
        em.flush();
        em.clear();

        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(loaded).as("패치 조인 미적용").isFalse();
    }

    @Test
    public void fetchJoinUse() {
        em.flush();
        em.clear();

        Member findMember = queryFactory
                .selectFrom(member)
                .join(member.team, team)
                .fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(loaded).as("패치 조인 적용").isTrue();
    }

    /**
     * 나이가 가장 많은 회원 조회
     */
    @Test
    public void subQuery() {
        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        /* JPAExpressions : 서브쿼리 */
                        select(memberSub.age.max())
                                .from(memberSub)
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(40);
    }

    /**
     * 나이가 평균 이상인 회원
     */
    @Test
    public void subQuery2() {
        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.goe(
                        select(memberSub.age.avg())
                                .from(memberSub)
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(30, 40);
    }

    @Test
    public void basicCase() {
        List<String> result = queryFactory
                .select(member.age
                        .when(10).then("열살")
                        .when(20).then("스무살")
                        .when(30).then("서른살")
                        .otherwise("기타")
                )
                .from(member)
                .fetch();

        result.forEach(System.out::println);
    }

    @Test
    public void complexCase() {
        // 복잡한 case -> CaseBuilder 사용
        List<String> result = queryFactory
                .select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("0 ~ 20")
                        .when(member.age.between(21, 40)).then("21 ~ 40")
                        .otherwise("기타")
                )
                .from(member)
                .fetch();

        result.forEach(System.out::println);
    }

    @Test
    public void constant() {
        List<Tuple> result = queryFactory
                .select(member.username, Expressions.constant("A"))
                .from(member)
                .fetch();

        result.forEach(System.out::println);
    }

    @Test
    public void concat() {
        List<String> result = queryFactory
                .select(member.username.concat("_").concat(member.age.stringValue()))   // Integer -> String
                .from(member)
                .fetch();

        result.forEach(System.out::println);
    }
}

