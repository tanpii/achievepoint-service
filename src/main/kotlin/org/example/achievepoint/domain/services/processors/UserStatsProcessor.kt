package org.example.achievepoint.domain.services.processors

import org.example.achievepoint.domain.models.dto.UserStats
import org.example.achievepoint.domain.models.types.StatsType

interface UserStatsProcessor {
    val statsType: StatsType

    fun processStats(userStats: UserStats)
}
