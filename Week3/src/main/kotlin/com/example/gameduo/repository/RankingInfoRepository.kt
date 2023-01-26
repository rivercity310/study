package com.example.gameduo.repository

import com.example.gameduo.dto.RankingInfo
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RankingInfoRepository: CrudRepository<RankingInfo, String> {
}