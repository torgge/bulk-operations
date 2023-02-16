package com.bonespirito.bulkoperations.infrastructure.resource

import com.bonespirito.bulkoperations.domain.model.Order
import com.bonespirito.bulkoperations.infrastructure.messaging.rabbitmq.producer.OrderMessageProducer
import com.bonespirito.bulkoperations.utils.toPayload
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders", produces = [MediaType.APPLICATION_JSON_VALUE])
class OrderResource(
    @Autowired val messageService: OrderMessageProducer,
) {
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun postMessage(
        @RequestBody message: Order,
    ): HttpEntity<Any?> {
        messageService.produce(message.toPayload())
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(message)
    }
}
