package com.bonespirito.bulkoperations.domain

import java.time.LocalDate

data class Order(
    val id: Long,
    val items: List<Material>?,
    val createAt: LocalDate
)

data class Material(
    val skuCode: String,
    val quantity: Int,
    val buisinessUnit: String
)
