package com.bonespirito.bulkoperations.infrastructure.messaging.rabbitmq.config

import org.hibernate.criterion.Order
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RabbitMessageService(
    private val rabbitTemplate: RabbitTemplate,
    private val messageConverter: MessageConverter
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Value("\${spring.rabbitmq.order.exchange}")
    val exchange: String = ""
    @Value("\${spring.rabbitmq.order.routing-key}")
    val routingKey: String = ""

    @RabbitListener(queues = ["\${spring.rabbitmq.order.queue}"])
    fun listen(message: Message) {
        log.info("received message from ${message.messageProperties.consumerQueue}")
        val order = messageConverter.fromMessage(message) as Order
        log.info("body $order")
    }

    fun sendMessage(msg: String) = this.rabbitTemplate.convertAndSend(exchange, routingKey, msg)
}
