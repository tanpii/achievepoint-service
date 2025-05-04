package org.example.achievepoint.domain.services.processors

import org.example.achievepoint.domain.models.dto.ProcessStatsData
import org.example.achievepoint.domain.models.dto.UserStats
import org.example.achievepoint.support.json.JsonUtils
import kotlin.reflect.KClass

abstract class AbstractUserStatsProcessor<T : ProcessStatsData> : UserStatsProcessor {

    final override fun processStats(userStats: UserStats) {
        val a = JsonUtils.convertObject(userStats, processDataClassType)
        processStatsInternal(a)
    }

    abstract val processDataClassType: KClass<T>

    abstract fun processStatsInternal(processStatsData: T)
}
