package org.example.achievepoint.listeners

import io.github.oshai.kotlinlogging.KotlinLogging
import org.example.achievepoint.domain.models.dto.UserStats
import org.example.achievepoint.domain.services.UserStatsService
import org.example.achievepoint.support.json.JsonUtils
import org.example.achievepoint.support.kafka.DEFAULT_CONSUMER_FACTORY
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.Message
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger { }

@Component
class UserStatsEventListener(
    private val userStatsService: UserStatsService,
) {

    @KafkaListener(
        topics = ["\${kafka.topics.stats.destination}"],
        containerFactory = DEFAULT_CONSUMER_FACTORY,
        groupId = "\${kafka.topics.stats.groupId}"
    )
    fun processStats(event: Message<String>) {
        logger.info { "текст)" }
        val userStats = parseEvent(event.payload)

        userStatsService.processStats(userStats)
    }

    private fun parseEvent(payload: String): UserStats = JsonUtils.fromJson(payload, UserStats::class)
}
