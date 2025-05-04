package org.example.achievepoint.domain.models.dto

import org.example.achievepoint.domain.models.types.AchievementType
import java.time.Instant

data class UserAchievement(
    val achievementType: AchievementType,
    val achievedAt: Instant
)
