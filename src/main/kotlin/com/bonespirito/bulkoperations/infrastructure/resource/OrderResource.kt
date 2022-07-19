package com.bonespirito.bulkoperations.infrastructure.resource

import com.bonespirito.bulkoperations.application.RabbitMessageService
import com.bonespirito.bulkoperations.domain.model.Order
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders", produces = [MediaType.APPLICATION_JSON_VALUE])
class OrderResource(
    @Autowired val messageService: RabbitMessageService,
) {
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun postMessage(
        @RequestBody message: Order,
    ): HttpEntity<Any?> {
        messageService.sendMessage(message)
        return ResponseEntity.ok().build()
    }
}
