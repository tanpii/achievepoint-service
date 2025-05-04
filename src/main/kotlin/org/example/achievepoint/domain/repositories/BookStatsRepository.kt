package org.example.achievepoint.domain.repositories

import org.example.achievepoint.domain.models.entity.BookStatsEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BookStatsRepository : CrudRepository<BookStatsEntity, Long> {

    fun findByUserId(userId: UUID): BookStatsEntity?
}
