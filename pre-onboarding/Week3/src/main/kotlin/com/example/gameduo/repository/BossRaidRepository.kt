package com.example.gameduo.repository

import com.example.gameduo.entity.BossRaid
import jakarta.persistence.EntityManager
import jakarta.persistence.NoResultException
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository
import kotlin.Exception

@Repository
class BossRaidRepository(@PersistenceContext private val em: EntityManager) {
    internal fun save(raid: BossRaid) =
        em.persist(raid)

    internal fun findRaid(raidRecordId: Long): BossRaid =
        try { em.find(BossRaid::class.java, raidRecordId) }
        catch (e: Exception) { BossRaid(id = -1) }

    internal fun findEndTimeIsNullRaid(): BossRaid =
        try { em.createQuery("select r from BossRaid r where r.endTime = NULL", BossRaid::class.java)
                .singleResult }
        catch (e: NoResultException) { BossRaid(id = -1) }   /* 첫 레이드 시작시 exception 발생 */
}