package com.bonespirito.bulkoperations.domain

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class OrderPayloadRequest(
    val id: Long,
    val items: List<Material>?,
    val createdAt: LocalDate
)

@Serializable
data class MaterialPayloadRequestItem(
    val skuCode: String,
    val quantity: Int,
    val businessUnit: String
)
