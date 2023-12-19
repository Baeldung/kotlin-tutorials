package com.baeldung.ktorm

import org.junit.jupiter.api.Test
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.*
import org.ktorm.support.mysql.MySqlDialect
import org.ktorm.support.mysql.bulkInsertOrUpdate
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import org.testcontainers.utility.MountableFile
import java.time.Instant
import kotlin.test.assertEquals

@Testcontainers
class KtormLiveTest {

    private val ordersTable = OrdersTable()

    private val itemsTable = ItemsTable()

    private val customersTable = CustomersTable()

    private val Database.orders get() = this.sequenceOf(Orders)

    private val Database.customers get() = this.sequenceOf(Customers)

    private val Database.items get() = this.sequenceOf(Items)

    @Container
    private val mySQLContainer: MySQLContainer<*> = MySQLContainer(DockerImageName.parse("mysql"))
        .withDatabaseName("KTORM_TEST")
        .withUsername("root")
        .withPassword("root")
        .withExposedPorts(3306)
        .withCopyToContainer(MountableFile.forClasspathResource("/com/baeldung/ktorm/ktorm_domain.sql"), "/docker-entrypoint-initdb.d/1.sql")

    @Test
    fun `Uses SQL DSL to create a basic selection query`() {
        val database = connect()
        database.prepareTablesData()

        val query = database
            .from(ordersTable)
            .select(ordersTable.id, ordersTable.card, ordersTable.amount)

        assertEquals(10, query.totalRecordsInAllPages)
    }

    @Test
    fun `Uses SQL DSL to create a conditional selection query`() {
        val database = connect()
        database.prepareTablesData()

        val query = database
            .from(ordersTable)
            .select(ordersTable.id, ordersTable.card, ordersTable.amount)
            .where(ordersTable.itemId eq 1)

        assertEquals(1, query.totalRecordsInAllPages)
    }

    @Test
    fun `Uses SQL DSL to create a conditional selection query using multiple filtering criteria`() {
        val database = connect()
        database.prepareTablesData()

        val query = database
            .from(ordersTable)
            .select(ordersTable.id, ordersTable.card, ordersTable.amount)
            .whereWithConditions {
                it += (ordersTable.itemId eq 1)
                it += (ordersTable.customerId eq 1)
            }

        assertEquals(1, query.totalRecordsInAllPages)
    }

    @Test
    fun `Uses SQL DSL to join multiple tables`() {
        val database = connect()
        database.prepareTablesData()

        val query = database
            .from(ordersTable)
            .innerJoin(customersTable, on = ordersTable.customerId eq customersTable.id)
            .innerJoin(itemsTable, on = ordersTable.itemId eq itemsTable.id )
            .select(ordersTable.id, customersTable.email, itemsTable.description)

        assertEquals(1, query.totalRecordsInAllPages)
    }

    @Test
    fun `Uses SQL DSL to create a query for data aggregation`() {
        val database = connect()
        database.prepareTablesData()

        val query = database
            .from(ordersTable)
            .innerJoin(customersTable, on = ordersTable.customerId eq customersTable.id)
            .innerJoin(itemsTable, on = ordersTable.itemId eq itemsTable.id )
            .select(customersTable.id, itemsTable.id, sum(ordersTable.amount).aliased("item_sales"))
            .groupBy(customersTable.id, itemsTable.id)
            .having(itemsTable.id eq 1)

        assertEquals(1, query.totalRecordsInAllPages)
    }

    @Test
    fun `Uses Entity API to retrieve the list of Orders`() {
        val database = connect()
        database.prepareEntities()

        val orders = database
            .orders
            .toList()

        assertEquals(10, orders.size)
    }

    @Test
    fun `Uses Entity API to filter Order entities`() {
        val database = connect()
        database.prepareEntities()

        val filteredOrderEntities = database
            .orders
            .filter { it.itemId eq 1 }
            .toList()

        assertEquals(1, filteredOrderEntities.size)
    }

    @Test
    fun `Uses Entity API to filter Order entities using multiple criteria`() {
        val database = connect()
        database.prepareEntities()

        val multiFilteredOrderEntities = database
            .orders
            .filter { it.itemId eq 1 }
            .filter { it.customerId eq 1 }
            .toList()

        assertEquals(1, multiFilteredOrderEntities.size)
    }

    private fun connect(): Database {
        return Database.connect(
            url = "jdbc:mysql://${mySQLContainer.host}:${mySQLContainer.getMappedPort(3306)}/KTORM_TEST",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = "root",
            dialect = MySqlDialect()
        )
    }

    private fun Database.prepareTablesData() {
        batchInsert(itemsTable) {
            (1..10).forEach { idx ->
                item {
                    set(it.description, "test_item_$idx")
                }
            }
        }

        bulkInsertOrUpdate(customersTable) {
            (1..10).forEach { _ ->
                item {
                    set(it.email, "testuser@example.com")
                }

                onDuplicateKey { set(it.email, it.email) }
            }
        }

        batchInsert(ordersTable) {
            (1..10).forEach { idx ->
                item {
                    set(it.itemId, idx)
                    set(it.customerId, idx)
                    set(it.amount, 100.20.toBigDecimal())
                    set(it.card, "Card nr. $idx")
                    set(it.timestamp, Instant.now())
                }
            }
        }
    }

    private fun Database.prepareEntities() {
        (1..10).forEach { idx ->
            val item = Item {
                description = "test_item_$idx"
            }

            items.add(item)
        }

        var customer = customers.find {
            it.email eq "testuser@example.com"
        }

        if (customer == null) {

            customer = Customer {
                email = "testuser@example.com"
            }

            customers.add(customer)
        }

        (1..10).forEach { idx ->
            val order = Order()
            val item = items.find { it.id eq idx } ?: items.first()
            order.item = item
            order.customer = customer
            order.amount = 100.20.toBigDecimal()
            order.card = "Card nr. $idx"
            order.timestamp = Instant.now()
            orders.add(order)
        }
    }
}