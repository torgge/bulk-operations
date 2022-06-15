package com.bonespirito.bulkoperations.domain

fun Order.toPayload() = OrderPayloadRequest (
    id = this.id,
    items = this.items,
    createdAt = this.createdAt
)
