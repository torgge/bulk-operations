package com.bonespirito.bulkoperations.domain.model

import java.time.LocalDateTime

data class Order(
    val id: Long?,
    val items: List<Material>,
    val createdAt: LocalDateTime
)

data class Material(
    val id: Long?,
    val orderId: Long?,
    val skuCode: String,
    val quantity: Int,
    val brand: String
)
