package com.bonespirito.bulkoperations.domain

import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME

fun Order.toPayload() = OrderPayloadRequest (
    id = this.id,
    items = this.items?.map { it.toPayload() },
    createdAt = this.createdAt.format(ISO_LOCAL_DATE_TIME)
)

fun Material.toPayload() = MaterialPayloadRequestItem (
    skuCode = this.skuCode,
    quantity = this.quantity,
    businessUnit = this.businessUnit
)


