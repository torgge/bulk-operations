package com.bonespirito.bulkoperations.infrastructure.resource

import com.bonespirito.bulkoperations.infrastructure.messaging.rabbitmq.config.RabbitMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderResource(
    @Autowired
    val messageService: RabbitMessageService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postMessage(
        @RequestParam("message") message: String
    ): HttpEntity<Any?> {
        messageService.sendMessage(message)
        return ResponseEntity.ok().build()
    }
}
