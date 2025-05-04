package org.example.achievepoint.domain.models.entity

import org.example.achievepoint.domain.models.dto.CommentStats
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table(name = "comment_stats")
data class CommentStatsEntity(
    val userId: UUID,
) {
    @Id
    var id: Long? = null
    var commentsLeft: Int = 0
    var goodRateCount: Int = 0
    var badRateCount: Int = 0

    fun addRateCount(rating: Int) {
        when (rating) {
            1 -> badRateCount += 1
            5 -> goodRateCount += 1
        }
    }

    fun toDto() = CommentStats(
        commentsLeft,
        goodRateCount,
        badRateCount
    )
}
