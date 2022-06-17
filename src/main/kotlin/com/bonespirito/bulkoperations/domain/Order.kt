package com.bonespirito.bulkoperations.domain

import java.time.LocalDateTime

data class Order(
    val id: Long,
    val items: List<Material>?,
    val createdAt: LocalDateTime
)

data class Material(
    val skuCode: String,
    val quantity: Int,
    val businessUnit: String
)
