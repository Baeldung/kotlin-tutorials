package com.baeldung.kotest.testcontainers

import io.kotest.core.extensions.install
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.testcontainers.ContainerLifecycleMode
import io.kotest.extensions.testcontainers.JdbcDatabaseContainerExtension
import io.kotest.matchers.shouldBe
import org.testcontainers.containers.MySQLContainer
import javax.sql.DataSource

class TestContainersSamples : FunSpec({
    val mysql = MySQLContainer("mysql:8")
    val exposedMySQL = MySQLContainer("mysql:8").apply {
        withExposedPorts(3306)
    }

    val dataSource: DataSource = install(
        JdbcDatabaseContainerExtension(
            mysql,
            mode = ContainerLifecycleMode.Spec,
            beforeStart = {},
            afterStart = {},
            beforeShutdown = {})
    )

    val service = DatabaseService(dataSource)

    test("Inserting in database should persist an object") {
        service.insert(Person("Leo"))
        service.insert(Person("Colman"))

        service.all() shouldBe listOf(Person("Leo"), Person("Colman"))
    }
})
