package com.example.gameduo.parser

import com.example.gameduo.dto.StaticDataDTO
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.json.simple.parser.ParseException
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.DefaultUriBuilderFactory

object StaticDataAPIParser {
    private fun call(): String? {
        val url: String = "https://dmpilf5svl7rv.cloudfront.net/assignment/backend/bossRaidData.json"
        val factory: DefaultUriBuilderFactory = DefaultUriBuilderFactory(url)
        factory.encodingMode = DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY

        return WebClient.builder()
            .uriBuilderFactory(factory)
            .baseUrl(url)
            .build()
            .get()
            .retrieve()
            .bodyToMono(String::class.java)
            .block()
    }

    private fun parsing(): StaticDataDTO {
        val scoreMap: MutableMap<Long, Long> = hashMapOf()

        val staticDataDTO = StaticDataDTO()
        val jsonStr = call() ?: return staticDataDTO
        val jsonParser = JSONParser()

        try {
            val result: Any = jsonParser.parse(jsonStr)

            if (result is JSONObject) {
                val bossRaids = result["bossRaids"] as JSONArray
                val bossRaidObj = bossRaids[0] as JSONObject
                val bossRaidLimitSeconds: Long = bossRaidObj["bossRaidLimitSeconds"] as Long
                val levelsArr = bossRaidObj["levels"] as JSONArray

                for (level in levelsArr) {
                    val it = level as JSONObject
                    val lev = it["level"] as Long
                    val score = it["score"] as Long
                    scoreMap[lev] = score
                }

                staticDataDTO.bossRaidLimitSeconds = bossRaidLimitSeconds
                staticDataDTO.levels = scoreMap
            }

        } catch (e: ParseException) { e.stackTrace }

        return staticDataDTO
    }

    internal fun getStaticData(): StaticDataDTO = parsing()
}