package com.bonespirito.bulkoperations.domain

import kotlinx.serialization.Serializable

@Serializable
data class OrderPayloadRequest(
    val id: Long,
    val items: List<MaterialPayloadRequestItem>?,
    val createdAt: String
)

@Serializable
data class MaterialPayloadRequestItem(
    val skuCode: String,
    val quantity: Int,
    val businessUnit: String
)
