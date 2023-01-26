package com.bonespirito.bulkoperations

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.envers.repository.config.EnableEnversRepositories

@EnableEnversRepositories
@SpringBootApplication
class BulkOperationsApplication

fun main(args: Array<String>) {
    runApplication<BulkOperationsApplication>(*args)
}
