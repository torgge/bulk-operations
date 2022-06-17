package com.bonespirito.bulkoperations.application

import com.bonespirito.bulkoperations.domain.Order
import com.bonespirito.bulkoperations.domain.OrderPayloadRequest
import com.bonespirito.bulkoperations.domain.toPayload
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RabbitMessageService(
    private val rabbitTemplate: RabbitTemplate,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Value("\${spring.rabbitmq.order.exchange}")
    val exchange: String = ""

    @Value("\${spring.rabbitmq.order.routing-key}")
    val routingKey: String = ""

    private val json = Json { ignoreUnknownKeys = true; isLenient = true }

    @RabbitListener(queues = ["ORDER-QUEUE"])
    fun listen(message: Message) {
        log.info("received message from message $message")
        val body: OrderPayloadRequest = json.decodeFromString(String(message.body))
        log.info("body $body")
    }

    fun sendMessage(msg: Order) {
        log.info("publishing message: $msg")
        this.rabbitTemplate.convertAndSend(exchange, routingKey, msg.toPayload())
        log.info("### published payload: ${msg.toPayload()} ###")
    }
}
