package com.example.gameduo.entity

import com.example.gameduo.dto.BossRaidDTO
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize

@Entity
@Table(name = "game_user")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long = 0L,

    @BatchSize(size = 5)
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var bossRaidHistory: MutableList<BossRaid> = arrayListOf()
) {
    internal fun getTotalScore(): Long {
        var totalScore: Long = 0
        bossRaidHistory.forEach { totalScore += it.score }
        return totalScore
    }

    internal fun getBossRaidDTOHistory(): List<BossRaidDTO> {
        return this.bossRaidHistory.map { BossRaidDTO(
            raidRecordId = it.id,
            score = it.score,
            enterTime = it.enterTime,
            endTime = it.endTime
        ) }
    }
}
