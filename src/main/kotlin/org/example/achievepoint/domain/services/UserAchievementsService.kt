package org.example.achievepoint.domain.services

import org.example.achievepoint.controllers.v1.json.UserAchievementsResponse
import org.example.achievepoint.domain.models.dto.BookStats
import org.example.achievepoint.domain.models.dto.CommentStats
import org.example.achievepoint.domain.models.entity.AchievementEntity
import org.example.achievepoint.domain.models.types.AchievementType
import org.example.achievepoint.domain.repositories.AchievementRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserAchievementsService(
    private val achievementRepository: AchievementRepository
) {

    fun getUserAchievements(userId: UUID) = achievementRepository.findByUserId(userId).map { it.toDto() }.let {
        UserAchievementsResponse(
            userId = userId,
            achievements = it
        )
    }

    @Transactional
    fun processBookAchievements(userId: UUID, bookStats: BookStats) {
        val userAchievements = achievementRepository.findByUserId(userId).map { it.achievementType }

        val newAchievementTypes = mutableListOf<AchievementType>()

        newAchievementTypes += AchievementType.BOOK_READ_ACHIEVEMENTS.filter { it.condition(bookStats.booksRead) }
        newAchievementTypes += AchievementType.MIN_READ_TIME_ACHIEVEMENTS.filter {
            it.condition(bookStats.minDaysReadTime)
        }

        val newAchievements = newAchievementTypes.filter { it !in userAchievements }
            .map { AchievementEntity(userId, it) }

        achievementRepository.saveAll(newAchievements)
    }

    @Transactional
    fun processCommentAchievements(userId: UUID, commentStats: CommentStats) {
        val userAchievements = achievementRepository.findByUserId(userId).map { it.achievementType }

        val newAchievementTypes = mutableListOf<AchievementType>()

        newAchievementTypes += AchievementType.COMMENT_ACHIEVEMENTS.filter { it.condition(commentStats.commentsLeft) }
        newAchievementTypes += AchievementType.GOOD_COMMENT_ACHIEVEMENTS.filter {
            it.condition(commentStats.goodRateCount)
        }
        newAchievementTypes += AchievementType.BAD_COMMENT_ACHIEVEMENTS.filter {
            it.condition(commentStats.badRateCount)
        }

        val newAchievements = newAchievementTypes.filter { it !in userAchievements }
            .map { AchievementEntity(userId, it) }

        achievementRepository.saveAll(newAchievements)
    }
}
