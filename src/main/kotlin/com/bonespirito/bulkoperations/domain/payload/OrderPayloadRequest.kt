package com.bonespirito.bulkoperations.domain.payload

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

interface MessagePayload

@Serializable
data class OrderPayloadRequest(
    val id: Long?,
    @Required
    val items: List<MaterialPayloadRequestItem>?,
    val createdAt: String
) : MessagePayload

@Serializable
data class MaterialPayloadRequestItem(
    val id: Long?,
    @Required
    val skuCode: String,
    @Required
    val quantity: Int,
    @Required
    val brand: String
) : MessagePayload
