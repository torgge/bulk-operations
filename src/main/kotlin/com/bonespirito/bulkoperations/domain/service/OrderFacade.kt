package com.bonespirito.bulkoperations.domain.service

import com.bonespirito.bulkoperations.domain.model.Order

interface OrderFacade {
    fun process(order: Order)
}
