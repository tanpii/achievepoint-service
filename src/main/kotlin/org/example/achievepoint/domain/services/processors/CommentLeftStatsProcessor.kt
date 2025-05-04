package org.example.achievepoint.domain.services.processors

import org.example.achievepoint.domain.models.dto.ProcessStatsData
import org.example.achievepoint.domain.models.types.StatsType
import org.example.achievepoint.domain.services.UserAchievementsService
import org.example.achievepoint.domain.services.component.CommentStatsComponent
import org.springframework.stereotype.Component
import java.util.*

@Component
class CommentLeftStatsProcessor(
    private val commentStatsComponent: CommentStatsComponent,
    private val achievementsService: UserAchievementsService
) : AbstractUserStatsProcessor<CommentLeftStats>() {

    override val statsType = StatsType.COMMENT_LEFT

    override val processDataClassType = CommentLeftStats::class

    override fun processStatsInternal(processStatsData: CommentLeftStats) {
        val commentStats = commentStatsComponent.saveOrUpdateCommentStats(processStatsData).toDto()
        achievementsService.processCommentAchievements(processStatsData.userId, commentStats)
    }
}

data class CommentLeftStats(
    override val userId: UUID,
    override val statsType: StatsType,
    val stats: RateStats
) : ProcessStatsData {
    data class RateStats(val rating: Int)
}
