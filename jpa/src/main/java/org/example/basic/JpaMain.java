package org.example.basic;

import org.example.shop.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hello");

    private static void mappedClassTest() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Book book = new Book();
            book.setAuthor("황승수");
            book.setIsbn("123123");

            em.persist(book);

            em.flush();
            em.clear();

            Book findBook = em.find(Book.class, book.getId());
            System.out.println(findBook);

            tx.commit();
        }

        catch (Exception e) {
            tx.rollback();
        }

        finally {
            em.close();
        }
    }

    private static void insert() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());      // 1차 캐시에서 가져옴
            List<Member> members = findMember.getTeam().getMembers();
            
            for (Member mem : members) System.out.println("member.getUsername() = " + mem.getUsername());
            
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
            member.setUsername("sudang");

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
        mappedClassTest();
    }
}