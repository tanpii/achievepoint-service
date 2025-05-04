package org.example.achievepoint.support.kafka

import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "kafka")
class AchievePointKafkaProperties {
    var consumersEnabled: Boolean = false
    var clusters: Map<String, KafkaProperties> = mutableMapOf()
}
