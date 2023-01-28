package com.example.restapi.api

import com.example.restapi.domain.Address
import com.example.restapi.domain.Member
import com.example.restapi.service.MemberService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberApiController(private val memberService: MemberService) {

    /* 등록 api V1: 엔티티를 외부에 노출하지 말것 -> DTO 사용 */
    @PostMapping("/api/v1/members")
    internal fun saveMemberV1(@RequestBody @Valid member: Member): CreateMemberResponse {
        val id: Long = memberService.join(member)
        return CreateMemberResponse(id, member.name)
    }

    /* 들옥 api V2: DTO 사용 -> DTO에서 NotEmpty 등으로 validation 검사 후 엔티티에 매핑 -> 안정적 */
    @PostMapping("/api/v2/members")
    internal fun saveMemberV2(@RequestBody @Valid request: CreateMemberRequest): CreateMemberResponse {
        val member = Member()
        member.name = request.name
        member.address = request.address

        val id: Long = memberService.join(member)
        return CreateMemberResponse(id, member.name, member.address)
    }

    data class CreateMemberRequest(@NotEmpty val name: String, val address: Address?)
    data class CreateMemberResponse(val id: Long, val name: String?, val address: Address? = null)



    /* 수정 api */
    @PutMapping("/api/v2/members/{id}")
    internal fun updateMemberV2(
        @PathVariable("id") id: Long,
        @RequestBody @Valid request: UpdateMemberRequest): UpdateMemberResponse
    {
        memberService.update(id, request.name)
        val findMember = memberService.findOne(id)
        return UpdateMemberResponse(findMember.id!!, findMember.name!!)
    }

    data class UpdateMemberRequest(@NotEmpty val name: String)
    data class UpdateMemberResponse(val id: Long, val name: String)



    /* 조회 api V1: 엔티티 직접 노출하지 말것, 배열 형식으로 반환 -> JSON 확장성이 없어짐 */
    @GetMapping("/api/v1/members")
    internal fun membersV1(): List<Member> =
        memberService.findMembers()

    /* 조회 api V2: Entity -> DTO 변환, JSON 배열 -> Object 변환 (Object 내 배열이 있어야 확장성이 있음) */
    @GetMapping("/api/v2/members")
    internal fun membersV2(): Result<List<MemberDTO>> {
        val findMembers: List<Member> = memberService.findMembers()
        val collect: List<MemberDTO> = findMembers.map { MemberDTO(it.name!!) }
        return Result(collect.size, collect)
    }

    data class MemberDTO(val name: String)
    data class Result<T>(val count: Int, val data: T)



}