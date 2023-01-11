package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // 등록
        try {
            tx.begin();

            Member member = new Member();
            member.setId(1);
            member.setName("Test");

            em.persist(member);
            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }

        // 수정
        try {
            tx.begin();

            Member findMember = em.find(Member.class, 1);
            System.out.println("찾은 Member: " + findMember.getName());

            findMember.setName("Test2");
            // em.persist(findMember) -> 불필요

            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }

        try {
            tx.begin();

            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0)      // limit
                    .setMaxResults(5)       // offset
                    .getResultList();

            for (Member member : result)
                System.out.println(member.getName());

            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }

        // 삭제
        try {
            tx.begin();

            em.remove(em.find(Member.class, 1));

            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }
        finally {
            em.close();
        }

        emf.close();
    }
}