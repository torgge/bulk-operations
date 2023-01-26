package com.bonespirito.bulkoperations.application

import com.bonespirito.bulkoperations.domain.model.Order
import com.bonespirito.bulkoperations.domain.service.OrderService
import com.bonespirito.bulkoperations.infrastructure.persistence.MaterialRepository
import com.bonespirito.bulkoperations.infrastructure.persistence.OrderRepository
import com.bonespirito.bulkoperations.utils.toPersistent
import com.bonespirito.bulkoperations.utils.toVO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderServiceImp(
    @Autowired private val orderRepository: OrderRepository,
    @Autowired private val materialRepository: MaterialRepository
) : OrderService {

    private val log = LoggerFactory.getLogger(javaClass)

    @Transactional
    override fun save(order: Order): Order {
        log.info("\nTrying to save an order $order \n")

        try {
            val orderPersistent = order.toPersistent()

            val orderResult = orderRepository.save(orderPersistent)

            val materialsPersistent = order.items.map {
                it.copy(
                    orderId = orderResult.id
                ).toPersistent()
            }

            val materialsResult = materialRepository.saveAll(materialsPersistent)

            return orderPersistent.toVO(materialsResult.toList())
        } catch (e: Error) {
            log.error("${e.message}")
            throw Exception(e)
        }
    }

    @Transactional
    override fun bulkUpdate(orders: List<Order>): List<Order> {
        log.info("Trying to save an order list $orders")

        val ordersListResult = orders.map { order ->

            val orderPersistent = orderRepository.save(order.toPersistent())
            val materialsPersistent = order.items.map { it.copy(orderId = orderPersistent.id).toPersistent() }

            orderPersistent.toVO(materialsPersistent)
        }

        return ordersListResult
    }
}
