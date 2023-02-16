package com.bonespirito.bulkoperations.utils

import com.bonespirito.bulkoperations.domain.model.Material
import com.bonespirito.bulkoperations.domain.model.Order
import com.bonespirito.bulkoperations.domain.payload.MaterialPayloadRequestItem
import com.bonespirito.bulkoperations.domain.payload.OrderPayloadRequest
import com.bonespirito.bulkoperations.infrastructure.persistence.entity.MaterialPersistent
import com.bonespirito.bulkoperations.infrastructure.persistence.entity.OrderPersistent
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME

fun Order.toPayload() = OrderPayloadRequest(
    id = this.id,
    items = this.items.map { it.toPayload() },
    createdAt = this.createdAt.format(ISO_LOCAL_DATE_TIME),
)

fun Order.toPersistent() =
    OrderPersistent(
        id = this.id ?: 0L,
        createdAt = this.createdAt,
    )

fun OrderPayloadRequest.toVO() = Order(
    id = this.id,
    items = this.items!!.map { it.toVO(this.id) },
    createdAt = LocalDateTime.parse(this.createdAt),
)

fun OrderPersistent.toVO(items: List<MaterialPersistent>) = Order(
    id = this.id,
    items = items.map { it.toVO() },
    createdAt = this.createdAt,
)

fun Material.toPayload() = MaterialPayloadRequestItem(
    id = this.id,
    skuCode = this.skuCode,
    quantity = this.quantity,
    brand = this.brand,
)

fun Material.toPersistent() = MaterialPersistent(
    id = this.id,
    orderId = this.orderId,
    skuCode = this.skuCode,
    quantity = this.quantity,
    brand = this.brand,
)

fun MaterialPayloadRequestItem.toVO(orderId: Long?) = Material(
    id = this.id,
    orderId = orderId,
    skuCode = this.skuCode,
    quantity = this.quantity,
    brand = this.brand,
)

fun MaterialPersistent.toVO() = Material(
    id = this.id ?: 0L,
    orderId = this.orderId,
    skuCode = this.skuCode,
    quantity = this.quantity,
    brand = this.brand,
)
