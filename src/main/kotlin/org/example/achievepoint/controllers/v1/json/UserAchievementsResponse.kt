package org.example.achievepoint.controllers.v1.json

import org.example.achievepoint.domain.models.dto.UserAchievement
import java.util.*

data class UserAchievementsResponse(
    val userId: UUID,
    val achievements: List<UserAchievement>
)
