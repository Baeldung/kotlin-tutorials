package com.baeldung.testresources

import io.kotest.core.extensions.install
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.testcontainers.JdbcDatabaseContainerExtension
import io.kotest.matchers.shouldBe
import org.testcontainers.containers.PostgreSQLContainer

/**
 * livetest. This class requires a docker environment to be setup on one's computer so that testcontainers can use
 * it.
 */
class TestContainersResourceManagementLiveTest : StringSpec({
    val dataSource = install(JdbcDatabaseContainerExtension(PostgreSQLContainer<Nothing>("postgres:latest")))

    "test querying the database" {
        val result = dataSource.connection.use {
            it.createStatement().executeQuery("SELECT 1").use {
                it.next()
                it.getInt(1)
            }
        }

        result shouldBe 1
    }
})