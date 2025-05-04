package org.example.achievepoint.controllers.v1.json

import org.example.achievepoint.domain.models.dto.BookStats
import org.example.achievepoint.domain.models.dto.CommentStats
import java.util.*

data class UserStatsResponse(
    val userId: UUID,
    val bookStats: BookStats?,
    val commentStats: CommentStats?,
)
