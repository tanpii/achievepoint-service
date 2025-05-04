package org.example.achievepoint.domain.services

import org.example.achievepoint.controllers.v1.json.UserStatsResponse
import org.example.achievepoint.domain.models.dto.UserStats
import org.example.achievepoint.domain.models.types.StatsType
import org.example.achievepoint.domain.repositories.BookStatsRepository
import org.example.achievepoint.domain.repositories.CommentStatsRepository
import org.example.achievepoint.domain.services.processors.UserStatsProcessor
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserStatsService(
    private val bookStatsRepository: BookStatsRepository,
    private val commentStatsRepository: CommentStatsRepository,
    userStatsProcessors: List<UserStatsProcessor>,
) {
    private val userStatsProcessorsMap: Map<StatsType, UserStatsProcessor> =
        userStatsProcessors.associateBy { it.statsType }

    fun processStats(userStats: UserStats) {
        val processor = getProcessor(userStats.statsType)

        processor.processStats(userStats)
    }

    fun getUserStats(userId: UUID): UserStatsResponse {
        val bookStats = bookStatsRepository.findByUserId(userId)?.toDto()
        val commentStats = commentStatsRepository.findByUserId(userId)?.toDto()

        return UserStatsResponse(
            userId,
            bookStats,
            commentStats
        )
    }

    private fun getProcessor(statsType: StatsType): UserStatsProcessor =
        requireNotNull(userStatsProcessorsMap[statsType])
}
