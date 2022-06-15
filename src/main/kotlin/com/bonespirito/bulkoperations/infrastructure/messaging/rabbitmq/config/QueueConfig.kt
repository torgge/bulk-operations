package com.bonespirito.bulkoperations.infrastructure.messaging.rabbitmq.config

import org.springframework.amqp.core.*
import org.springframework.amqp.core.Binding
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueueConfig {

    @Value("\${spring.rabbitmq.order.routing-key}")
    val orderRoutingKey: String = ""

    @Value("\${spring.rabbitmq.order.queue}")
    val orderQueue: String = ""

    @Value("\${spring.rabbitmq.order.exchange}")
    val orderExchange: String = ""

    @Value("\${spring.rabbitmq.order.dlq-queue}")
    val dlqQueue: String = ""

    @Value("\${spring.rabbitmq.order.dlq-exchange}")
    val dlqExchange: String = ""

    @Value("\${spring.rabbitmq.order.dlq-routing-key}")
    val dlqRoutingKey: String = ""

    @Bean
    fun orderQueue(): Queue =
        QueueBuilder
            .durable(orderQueue)
            .withArgument("x-dead-letter-exchange", dlqExchange)
            .withArgument("x-dead-letter-routing-key", dlqRoutingKey)
            .deadLetterExchange(dlqExchange)
            .deadLetterRoutingKey(dlqRoutingKey)
            .build()

    @Bean
    fun orderDLQQueue(): Queue =
        QueueBuilder
            .durable(dlqQueue)
            .build()

    @Bean
    fun orderExchange(): DirectExchange =
        ExchangeBuilder
            .directExchange(orderExchange)
            .durable(true)
            .build()

    @Bean
    fun orderDLQExchange(): DirectExchange =
        ExchangeBuilder
            .directExchange(dlqExchange)
            .durable(true)
            .build()

    @Bean
    fun orderBinding(): Binding? = BindingBuilder
        .bind(orderQueue())
        .to(orderExchange())
        .with(orderRoutingKey)

    @Bean
    fun orderDLQBinding(): Binding? = BindingBuilder
        .bind(orderDLQQueue())
        .to(orderDLQExchange())
        .with(dlqRoutingKey)
}
