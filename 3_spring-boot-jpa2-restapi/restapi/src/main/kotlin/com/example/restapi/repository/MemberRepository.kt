package com.example.restapi.repository

import com.example.restapi.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
    // select m from Member m where m.name = ?
    fun findByName(name: String): List<Member>
}