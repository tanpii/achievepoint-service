package org.example.achievepoint.domain.models.dto

import org.example.achievepoint.domain.models.types.StatsType
import java.util.*

interface ProcessStatsData {
    val userId: UUID
    val statsType: StatsType
}
