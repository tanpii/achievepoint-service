package org.example.achievepoint.domain.models.entity

import org.example.achievepoint.domain.models.dto.BookStats
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*
import kotlin.math.min

@Table(name = "book_stats")
data class BookStatsEntity(
    val userId: UUID,
) {
    @Id
    var id: Long? = null
    var booksRead: Int = 0
    var minDaysReadTime: Int? = null

    fun setDaysReadTime(daysRead: Int) {
        minDaysReadTime = minDaysReadTime?.let { min(it, daysRead) } ?: daysRead
    }

    fun toDto(): BookStats = BookStats(
        booksRead,
        minDaysReadTime!!
    )
}
