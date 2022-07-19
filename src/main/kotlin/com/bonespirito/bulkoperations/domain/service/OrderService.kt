package com.bonespirito.bulkoperations.domain.service

import com.bonespirito.bulkoperations.domain.model.Order

interface OrderService {
    fun save(order: Order): Order
    fun bulkUpdate(orders: List<Order>): List<Order>
}
