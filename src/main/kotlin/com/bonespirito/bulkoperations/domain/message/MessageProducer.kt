package com.bonespirito.bulkoperations.domain.message

import com.bonespirito.bulkoperations.domain.payload.MessagePayload

interface MessageProducer {
    fun produce(payload: MessagePayload)
}
