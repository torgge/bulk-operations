package com.bonespirito.bulkoperations.infrastructure.messaging.rabbitmq.config

import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.QueueBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueueConfig {

    @Value("\${spring.rabbitmq.order.queue}")
    val orderQueue: String = ""
    @Value("\${spring.rabbitmq.order.dlq-exchange}")
    val dlqExchange: String = ""
    @Value("\${spring.rabbitmq.order.dlq-routing-key}")
    val dlqRoutingKey: String = ""

    @Bean
    fun jsonQueue(): Queue {
        return QueueBuilder
            .nonDurable(orderQueue)
            .deadLetterExchange(dlqExchange)
            .deadLetterRoutingKey(dlqRoutingKey)
            .build()
    }
}
