package org.example.achievepoint.domain.repositories

import org.example.achievepoint.domain.models.entity.AchievementEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AchievementRepository : CrudRepository<AchievementEntity, Long> {

    fun findByUserId(userId: UUID): Set<AchievementEntity>
}
