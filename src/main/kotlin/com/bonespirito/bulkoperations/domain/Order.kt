package com.bonespirito.bulkoperations.domain

import java.time.LocalDate

data class Order(
    val id: Long,
    val items: List<Material>?,
    val createdAt: LocalDate
)

data class Material(
    val skuCode: String,
    val quantity: Int,
    val businessUnit: String
)
