package com.bonespirito.bulkoperations.infrastructure.persistence

import com.bonespirito.bulkoperations.infrastructure.persistence.entity.OrderPersistent
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<OrderPersistent, Long>
