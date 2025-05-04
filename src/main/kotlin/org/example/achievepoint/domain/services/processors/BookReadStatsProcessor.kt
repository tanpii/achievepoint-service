package org.example.achievepoint.domain.services.processors

import org.example.achievepoint.domain.models.dto.ProcessStatsData
import org.example.achievepoint.domain.models.types.StatsType
import org.example.achievepoint.domain.services.UserAchievementsService
import org.example.achievepoint.domain.services.component.BookStatsComponent
import org.springframework.stereotype.Component
import java.util.*

@Component
class BookReadStatsProcessor(
    private val bookStatsComponent: BookStatsComponent,
    private val achievementsService: UserAchievementsService
) : AbstractUserStatsProcessor<ReadBookStats>() {

    override val statsType = StatsType.BOOK_READ

    override val processDataClassType = ReadBookStats::class

    override fun processStatsInternal(processStatsData: ReadBookStats) {
        val bookStats = bookStatsComponent.createOrUpdateBookStats(processStatsData).toDto()
        achievementsService.processBookAchievements(processStatsData.userId, bookStats)
    }
}

data class ReadBookStats(
    override val userId: UUID,
    override val statsType: StatsType,
    val stats: ReadTimeStats
) : ProcessStatsData {
    data class ReadTimeStats(
        val daysRead: Int,
    )
}
