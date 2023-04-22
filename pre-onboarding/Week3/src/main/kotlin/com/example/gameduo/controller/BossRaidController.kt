package com.example.gameduo.controller

import com.example.gameduo.dto.BossRaidDTO
import com.example.gameduo.dto.RaidStatusDTO
import com.example.gameduo.entity.RankingInfo
import com.example.gameduo.repository.RankingInfoRepository
import com.example.gameduo.service.BossRaidService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BossRaidController(
    private val bossRaidService: BossRaidService,
    private val rankingInfoRepository: RankingInfoRepository
) {
    @PostMapping("/bossRaid/enter")
    internal fun startRaid(@RequestBody jsonMap: Map<String, Long?>): BossRaidDTO {
        val userId: Long? = jsonMap["userId"]
        val level: Long? = jsonMap["level"]
        return bossRaidService.startRaid(userId, level)
    }

    @PatchMapping("/bossRaid/end")
    internal fun finishRaid(@RequestBody jsonMap: Map<String, Long?>) {
        val userId = jsonMap["userId"]
        val raidRecordId = jsonMap["raidRecordId"]
        bossRaidService.finishRaid(userId, raidRecordId)
    }

    @GetMapping("/bossRaid")
    internal fun getRaidStatus(): RaidStatusDTO =
        bossRaidService.getRaidStatus()

    @PostMapping("bossRaid/topRankerList")
    internal fun getRankerList(@RequestBody id: Map<String, Long>): ResponseEntity<Any> {
        val userId = id["userId"]

        val rankingList: List<RankingInfo> = rankingInfoRepository.findAll()
            .sortedByDescending { it.totalScore }

        var myRank: RankingInfo? = null
        var isFirst: Boolean = true
        for ((idx, rank) in rankingList.withIndex()) {
            rank.ranking = idx.toLong()
            if (isFirst && rank.userId == userId) {
                myRank = rank
                isFirst = false
            }
        }

        val result = Result(topRankerList = rankingList, myRankingInfo = myRank!!)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/bossRaid/saveRankInfo")
    internal fun saveRankInfo(@RequestBody rankingInfo: RankingInfo): ResponseEntity<Any> {
        val saved = rankingInfoRepository.save(rankingInfo)
        return ResponseEntity.ok(saved)
    }

    data class Result(
        val topRankerList: List<RankingInfo>,
        val myRankingInfo: RankingInfo
    )
}