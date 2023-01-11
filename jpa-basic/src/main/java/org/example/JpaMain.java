package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hello");

    private static void insert() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Member member = new Member();
            member.setId(1);
            member.setName("seungsu");

            em.persist(member);

            tx.commit();
        }

        catch (Exception e) {
            tx.rollback();
        }

        finally {
            em.close();
        }
    }

    private static void update() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Member member = em.find(Member.class, 1);
            member.setName("sudang");

            tx.commit();
        }

        catch (Exception e) {
            tx.rollback();
        }

        finally {
            em.close();
        }
    }

    private static void delete() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

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
    }

    private static void getList() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            List<Member> result = em.createQuery("select m from Member as m")
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result)
                System.out.println(member.getName());

            tx.commit();
        }

        catch (Exception e) {
            tx.rollback();
        }

        finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        insert();
        getList();
        update();
        //delete();
    }
}