package org.example.achievepoint.domain.models.dto

import org.example.achievepoint.domain.models.types.StatsType
import java.util.UUID

data class UserStats(
    val userId: UUID,
    val statsType: StatsType,
    val stats: Any?,
)
