package com.bonespirito.bulkoperations.infrastructure.messaging.rabbitmq.consumer

import com.bonespirito.bulkoperations.domain.message.MessageConsumer
import com.bonespirito.bulkoperations.domain.payload.OrderPayloadRequest
import com.bonespirito.bulkoperations.domain.service.OrderService
import com.bonespirito.bulkoperations.utils.toVO
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrderMessageConsumer(
    @Autowired private val orderService: OrderService
) : MessageConsumer {

    private val log = LoggerFactory.getLogger(javaClass)

    private val json = Json { ignoreUnknownKeys = true; isLenient = true }

    @RabbitListener(queues = ["ORDER-QUEUE"])
    override fun consume(message: Message) {
        log.info("received message from message $message")
        val body: OrderPayloadRequest = json.decodeFromString(String(message.body))
        this.orderService.save(body.toVO())
        log.info("body $body bodyVO ${body.toVO()}")
    }
}
