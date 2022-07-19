package com.bonespirito.bulkoperations.application

import com.bonespirito.bulkoperations.domain.message.MessageProducer
import com.bonespirito.bulkoperations.domain.model.Order
import com.bonespirito.bulkoperations.domain.service.OrderFacade
import com.bonespirito.bulkoperations.domain.service.OrderService
import com.bonespirito.bulkoperations.utils.toPayload
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderFacadeImp(
    @Autowired private val messageProducer: MessageProducer,
    @Autowired private val orderService: OrderService
) : OrderFacade {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun process(order: Order) {
        log.info("Trying to process an order $order")
        val processedOrder = orderService.save(order)
        messageProduce(processedOrder)
    }

    fun messageProduce(order: Order) {
        val payload = order.toPayload()
        log.info("Trying to produce a message from payload $payload")
        messageProducer.produce(payload)
    }
}
