package com.example.restapi.service

import com.example.restapi.domain.Member
import com.example.restapi.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException

@Service
@Transactional(readOnly = true)
class MemberService(private val memberRepository: MemberRepository) {
    @Transactional(readOnly = false)
    internal fun join(member: Member): Long {
        validateDuplicateMember(member)
        memberRepository.save(member)
        return member.id!!
    }

    @Transactional(readOnly = false)
    internal fun update(id: Long, name: String): Unit {
        /** 변경 감지 방식으로 업데이트 (em.merge 사용 X)
        영속 상태 엔티티의 값을 변경하면 JPA가 자동으로 UPDATE 쿼리를 날림 (tx 커밋 시점)
         */
        val member = memberRepository.findById(id).get()
        member.name = name
    }

    internal fun findMembers(): List<Member> =
        memberRepository.findAll()

    internal fun findOne(memberId: Long): Member =
        memberRepository.findById(memberId).get()

    private fun validateDuplicateMember(member: Member): Unit {
        memberRepository.findByName(member.name!!)
            .takeIf { it.isNotEmpty() }  /* takeIf: 객체 상태에 대한 검사를 호출 체인에 포함 */
            .run { throw IllegalStateException("이미 가입한 회원") }
    }
}