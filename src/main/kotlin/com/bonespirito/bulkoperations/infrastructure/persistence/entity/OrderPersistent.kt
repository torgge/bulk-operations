package com.bonespirito.bulkoperations.infrastructure.persistence.entity

import org.hibernate.envers.AuditTable
import org.hibernate.envers.Audited
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Audited
@Table(name = "order")
@AuditTable(value = "order_history")
class OrderPersistent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Long = 0L,
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now()
)

@Entity
@Audited
@Table(name = "material")
@AuditTable(value = "material_history")
class MaterialPersistent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Long? = 0L,
    @Column
    val orderId: Long? = 0L,
    @Column(length = 13)
    val skuCode: String,
    @Column
    val quantity: Int,
    @Column(length = 10)
    val brand: String
)
