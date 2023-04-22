package com.example.gameduo.service

import com.example.gameduo.caching.CachingStaticData
import com.example.gameduo.dto.BossRaidDTO
import com.example.gameduo.dto.RaidStatusDTO
import com.example.gameduo.entity.RankingInfo
import com.example.gameduo.entity.BossRaid
import com.example.gameduo.entity.User
import com.example.gameduo.repository.BossRaidRepository
import com.example.gameduo.repository.RankingInfoRepository
import com.example.gameduo.repository.UserRepository
import com.example.gameduo.validation.NullArgChecker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class BossRaidService @Autowired constructor(
    private val bossRaidRepository: BossRaidRepository,
    private val userRepository: UserRepository,
    private val cachingStaticData: CachingStaticData,
    private val rankingInfoRepository: RankingInfoRepository
) {

    private val nullArgChecker = NullArgChecker
    private val bossRaidLimitSeconds = cachingStaticData.getBossRaidLimitSeconds()
    private val levels = cachingStaticData.getLevels()
    private var canEnter: Boolean = true

    /*
     * [ 입장 가능 조건 ]
     * - 한번에 한명의 유저만 레이드 진행 가능
     * - 아무도 보스레이드를 실행한 기록이 없는 경우,
     * - 시작한 기록이 있다면 마지막으로 시작한 유저가 레이드를 종료 or 레이드 제한시간만큼 경과된 경우
     */
    @Transactional
    internal fun startRaid(userId: Long?, level: Long?): BossRaidDTO {
        /* null 인자가 넘어온 경우 예외 처리 */
        if (!nullArgChecker.check(userId, level))
            return BossRaidDTO(errorMsg = "잘못 입력하셨습니다", isEntered = false)

        /* userId에 해당하는 user가 없는 경우 */
        val user: User = userRepository.getUser(userId!!)
        if (user.userId == -1L)
            return BossRaidDTO(errorMsg = "존재하지 않는 userId", isEntered = false)

        /* 진행중인 레이드 존재할 때, 180초가 지났으면 자동 퇴장 시켜야함 */
        val findRaid = bossRaidRepository.findEndTimeIsNullRaid()
        if (findRaid.id != -1L) {
            val findEnterMinute = findRaid.enterTime!!.minute
            val nowMinute = LocalDateTime.now().minute

            /* 3분 경과 -> 자동 퇴장 & 클리어 실패이므로 0점 */
            if (nowMinute.minus(findEnterMinute) >= bossRaidLimitSeconds!!) {
                val findUserId = findRaid.user?.userId
                val findRaidRecordId = findRaid.id
                finishRaid(findUserId, findRaidRecordId, isClear = false)
            }
        }

        /* 레이드를 시작할 수 없는 경우 */
        if (!canEnter)
            return BossRaidDTO(errorMsg = "이미 다른 사용자가 레이드를 시작하였습니다", isEntered = false)

        /* 레이드 시작 */
        canEnter = false
        val raid = BossRaid(
            enterTime = LocalDateTime.now(),
            level = level
        )
        raid.addUser(user)

        bossRaidRepository.save(raid)
        return BossRaidDTO(isEntered = true, raidRecordId = raid.id)
    }

    @Transactional
    internal fun finishRaid(userId: Long?, raidRecordId: Long?, isClear: Boolean = true): Boolean {
        /* null 인자가 넘어온 경우 예외 처리 */
        if (!nullArgChecker.check(raidRecordId, userId)) return false

        val findRaid = bossRaidRepository.findRaid(raidRecordId!!)

        /* 끝낼 레이드가 존재하지 않는 경우 -> 레이드가 끝나면 endTime이 업데이트 된다 */
        if (findRaid.endTime != null) return false

        /* raidRecordId에 해당하는 기록이 없는 경우 || userId가 일치하지 않는 경우 */
        if (findRaid.id == -1L || findRaid.user?.userId != userId) return false


        /* 레이드 종료 */
        canEnter = true
        if (isClear) {
            findRaid.endTime = LocalDateTime.now()
            findRaid.score = when (findRaid.level) {
                0L -> levels[0]!!
                1L -> levels[1]!!
                2L -> levels[2]!!
                else -> throw IllegalStateException("허용되지 않는 레벨입니다")
            }
        }
        else {
            findRaid.endTime = findRaid.enterTime!!.plusMinutes(3)
            findRaid.score = 0
        }

        val rankingInfo = RankingInfo(
            userId = findRaid.user?.userId,
            totalScore = findRaid.user?.getTotalScore()
        )

        saveRankInfo(rankingInfo)
        return true
    }

    @Transactional
    internal fun getRaidStatus(): RaidStatusDTO {
        /* 레이드가 비어있으면 얼리 리턴 */
        if (canEnter) return RaidStatusDTO(canEnter = true)

        val findRaid = bossRaidRepository.findEndTimeIsNullRaid()
        return RaidStatusDTO(
            canEnter = false,
            enteredUserId = findRaid.user?.userId
        )
    }

    /* 레이드 종료시 랭킹 정보 캐싱 */
    @Transactional
    internal fun saveRankInfo(rankingInfo: RankingInfo) {
        rankingInfoRepository.save(rankingInfo)
    }
}
