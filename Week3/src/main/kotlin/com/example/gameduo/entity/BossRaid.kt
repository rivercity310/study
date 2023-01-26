package com.example.gameduo.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class BossRaid(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raid_record_id")
    var id: Long = 0L,
    var score: Long = 0L,
    var level: Long? = null,
    var enterTime: LocalDateTime? = null,
    var endTime: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null
) {

    /* 연관관계 편의 메서드 */
    internal fun addUser(user: User) {
        this.user = user
        user.bossRaidHistory += this
    }
}