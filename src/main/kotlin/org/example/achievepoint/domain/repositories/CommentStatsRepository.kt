package org.example.achievepoint.domain.repositories

import org.example.achievepoint.domain.models.entity.CommentStatsEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CommentStatsRepository : CrudRepository<CommentStatsEntity, Long> {

    fun findByUserId(userId: UUID): CommentStatsEntity?
}
