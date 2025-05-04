package org.example.achievepoint.controllers.v1

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.example.achievepoint.controllers.v1.json.UserStatsResponse
import org.example.achievepoint.domain.services.UserStatsService
import org.example.achievepoint.infrastructure.AuthpointService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/stats")
class UserStatsController(
    private val authpointService: AuthpointService,
    private val userStatsService: UserStatsService
) {

    @Operation(
        summary = "Получить свои достижения",
        description = """
            ## Требования авторизации
            **Требуется Bearer Token**  
            Endpoint ожидает валидный JWT токен в заголовке Authorization.
            
            ### Формат заголовка:
            ```
            Authorization: Bearer <ваш_jwt_токен>
            ```
            
            ### Как авторизоваться:
            1. Нажмите кнопку 'Authorize' (иконка замка)
            2. Введите ваш токен (без префикса 'Bearer')
            3. Swagger автоматически добавит нужный префикс
        """,
        security = [SecurityRequirement(name = "bearerAuth")],
        parameters = [
            Parameter(
                name = "Authorization",
                `in` = ParameterIn.HEADER,
                description = "JWT Bearer token для авторизации",
                required = true,
                hidden = true,
                schema = Schema(type = "string", format = "jwt"),
                example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
            )
        ]
    )
    @GetMapping("/self")
    fun getSelfUserStats(
        @RequestHeader("Authorization")
        @Parameter(hidden = true)
        token: String
    ): UserStatsResponse {
        return token.replace("Bearer ", "").let { jwtToken ->
            val userId = authpointService.getUserByToken(jwtToken).uuid
            userStatsService.getUserStats(UUID.fromString(userId))
        }
    }

    @GetMapping("/{userId}")
    fun getUserStats(@PathVariable userId: UUID): UserStatsResponse = userStatsService.getUserStats(userId)
}
