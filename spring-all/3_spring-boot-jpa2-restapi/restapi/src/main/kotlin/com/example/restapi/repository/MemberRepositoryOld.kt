package com.example.restapi.repository

import com.example.restapi.domain.Member
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryOld(@PersistenceContext val em: EntityManager) {
    internal fun save(member: Member) =
        em.persist(member)

    internal fun findOne(id: Long) =
        em.find(Member::class.java, id)

    internal fun findAll(): List<Member> =
        em.createQuery("select m from Member m", Member::class.java)
            .resultList

    internal fun findByName(name: String): List<Member> =
        em.createQuery("select m from Member m where m.name = :name", Member::class.java)
            .setParameter("name", name)
            .resultList
}