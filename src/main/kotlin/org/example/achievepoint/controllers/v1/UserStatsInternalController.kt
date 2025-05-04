package org.example.achievepoint.controllers.v1

import org.example.achievepoint.domain.models.dto.UserStats
import org.example.achievepoint.domain.services.UserStatsService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/internal/stats")
class UserStatsInternalController(
    private val userStatsService: UserStatsService
) {

    @PostMapping
    fun handleNewStats(
        @RequestHeader("X-INTERNAL-API") token: String,
        @RequestBody userStats: UserStats
    ) {
        if (token != "BOOK_POINT_INTERNAL_KEY") {
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied")
        }
        userStatsService.processStats(userStats)
    }
}