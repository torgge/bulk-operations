package com.bonespirito.bulkoperations.infrastructure.persistence

import com.bonespirito.bulkoperations.infrastructure.persistence.entity.MaterialPersistent
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.history.RevisionRepository
import org.springframework.stereotype.Repository

@Repository
interface MaterialRepository : CrudRepository<MaterialPersistent, Long>, RevisionRepository<MaterialPersistent, Long, Long>
