package com.example.gameduo.repository

import com.example.gameduo.entity.User
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class UserRepository(@PersistenceContext private val em: EntityManager) {
    /* [ 유저 생성 메서드 ]
     * 파라미터로 들어온 User 엔티티를 영속화 후 아이디 값 반환
     * User 엔티티의 id는 항상 널이 아님이 보장되므로 널 아님 단언문(!!)을 사용
     */
    internal fun createUser(user: User): Long {
        em.persist(user)
        return user.userId
    }


    /* [ 유저 조회 메서드 ]
    *  userId에 해당하는 유저가 없는 경우 NullPointerException 발생
    *       -> 애플리케이션이 중단되는 것을 막기위해 예외 발생시 id가 -1인 유저 객체 반환
    * */
    internal fun getUser(userId: Long): User =
        try { em.find(User::class.java, userId) }
        catch (e: NullPointerException) { User(userId = -1) }
}