package org.example.achievepoint.domain.models.entity

import org.example.achievepoint.domain.models.dto.UserAchievement
import org.example.achievepoint.domain.models.types.AchievementType
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*

@Table(name = "user_achievements")
data class AchievementEntity(
    val userId: UUID,
    val achievementType: AchievementType,
    val achievedAt: Instant = Instant.now()
) {

    @Id
    var id: Long? = null

    fun toDto() = UserAchievement(
        achievementType,
        achievedAt
    )
}
