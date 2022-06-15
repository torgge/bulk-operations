package com.bonespirito.bulkoperations.application

import com.bonespirito.bulkoperations.domain.Order
import com.bonespirito.bulkoperations.domain.toPayload
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
    private val messageConverter: MessageConverter,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Value("\${spring.rabbitmq.order.exchange}")
    val exchange: String = ""

    @Value("\${spring.rabbitmq.order.routing-key}")
    val routingKey: String = ""

    @RabbitListener(queues = ["ORDER-QUEUE"])
    fun listen(message: Message) {
        log.info("received message from ${message.messageProperties.consumerQueue}")
//        val body = Json.decodeFromString<OrderPayloadRequest>(message.body.toString())
//        val order = messageConverter.fromMessage(message)
        val body = message.body.toString()
        log.info("body $body")
    }

    fun sendMessage(msg: Order) {
        log.info("publishing message: $msg")
        this.rabbitTemplate.convertAndSend(exchange, routingKey, msg.toPayload())
        log.info("### published payload: ${msg.toPayload()} ###")
    }
}
