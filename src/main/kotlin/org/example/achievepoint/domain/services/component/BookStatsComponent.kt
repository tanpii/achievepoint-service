package org.example.achievepoint.domain.services.component

import org.example.achievepoint.domain.models.entity.BookStatsEntity
import org.example.achievepoint.domain.repositories.BookStatsRepository
import org.example.achievepoint.domain.services.processors.ReadBookStats
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class BookStatsComponent(
    private val bookStatsRepository: BookStatsRepository
) {

    @Transactional
    fun createOrUpdateBookStats(bookStats: ReadBookStats) =
        bookStatsRepository.findByUserId(bookStats.userId)?.let {
            updateBookStats(it, bookStats)
        } ?: createBookStats(bookStats)

    private fun updateBookStats(bookStats: BookStatsEntity, stats: ReadBookStats) =
        bookStatsRepository.save(
            bookStats.apply {
                this.booksRead += 1
                setDaysReadTime(stats.stats.daysRead)
            }
        )

    private fun createBookStats(bookStats: ReadBookStats) =
        bookStatsRepository.save(
            BookStatsEntity(userId = bookStats.userId).apply {
                this.booksRead += 1
                setDaysReadTime(bookStats.stats.daysRead)
            }
        )
}
