package org.example.achievepoint

import org.example.achievepoint.support.kafka.AchievePointKafkaProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@EnableConfigurationProperties(AchievePointKafkaProperties::class)
@SpringBootApplication
class AchievepointApplication

fun main(args: Array<String>) {
    runApplication<AchievepointApplication>(*args)
}
