package com.example.springdatajpa.demo.repository;

import com.example.springdatajpa.demo.dto.MemberDTO;
import com.example.springdatajpa.demo.entity.Member;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUserNameAndAgeGreaterThan(String userName, int age);

    // @Query(name = "Member.findByUserName")   --> 관례상 생략 가능
    List<Member> findByUserName(@Param("userName") String userName);

    @Query("select m from Member m where m.userName = :userName and m.age = :age")      // --> 실무 사용: 엔티티가 아닌 여기에 바로 적용 가능
    List<Member> findUser(@Param("userName") String userName, @Param("age") int age);

    @Query("select m.userName from Member m")
    List<String> findUserNameList();

    @Query("select new com.example.springdatajpa.demo.dto.MemberDTO(m.id, m.userName, t.teamName) from Member m join m.team t")
    List<MemberDTO> findMemberDTO();

    @Query("select m.userName from Member m where length(m.userName) in :collection")
    List<String> findUserNameListByCollection(@Param("collection") Collection<Integer> lst);

    /* 반환 타입으로 다음 세가지 모두 가능 */
    List<Member> findListByAge(int age);            // 컬렉션 -> 결과 없어도 빈 컬렉션을 반환해줌, null 가능성 없음
    Member findByAge(int age);                      // 단건 -> null 가능성 있음, Optional 쓰기
    Optional<Member> findOptionalByAge(int age);    // 단건 Optional

    Page<Member> findPageByAge(int age, Pageable pageable);

    Slice<Member> findSliceByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true)  // executeUpdate 실행
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    Integer bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = { "team" })
    List<Member> findAll();

    @EntityGraph(attributePaths = { "team" })
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    // @EntityGraph(attributePaths = { "team" })
    @EntityGraph("Member.all")
    List<Member> findEntityGraphByUserName(@Param("userName") String userName);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Optional<Member> findReadOnlyByUserName(String userName);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUserName(String userName);
}
