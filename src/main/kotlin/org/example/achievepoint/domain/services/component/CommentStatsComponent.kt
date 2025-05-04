package org.example.achievepoint.domain.services.component

import org.example.achievepoint.domain.models.entity.CommentStatsEntity
import org.example.achievepoint.domain.repositories.CommentStatsRepository
import org.example.achievepoint.domain.services.processors.CommentLeftStats
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CommentStatsComponent(
    private val commentStatsRepository: CommentStatsRepository
) {

    @Transactional
    fun saveOrUpdateCommentStats(commentStats: CommentLeftStats) =
        commentStatsRepository.findByUserId(commentStats.userId)?.let {
            updateStats(it, commentStats)
        } ?: createStats(commentStats)

    private fun updateStats(commentStatsEntity: CommentStatsEntity, commentStats: CommentLeftStats) =
        commentStatsRepository.save(
            commentStatsEntity.apply {
                commentsLeft += 1
                addRateCount(commentStats.stats.rating)
            }
        )

    private fun createStats(commentStats: CommentLeftStats) =
        commentStatsRepository.save(
            CommentStatsEntity(userId = commentStats.userId).apply {
                commentsLeft += 1
                addRateCount(commentStats.stats.rating)
            }
        )
}
