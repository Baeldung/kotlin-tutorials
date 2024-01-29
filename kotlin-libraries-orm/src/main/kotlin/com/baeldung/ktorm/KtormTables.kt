package com.baeldung.ktorm

import org.ktorm.schema.*

class OrdersTable: Table<Nothing>("orders") {
    val id = int("id").primaryKey()

    val itemId = int("item_id")

    val customerId = int("customer_id")

    val card = base64("free_text_card", Charsets.UTF_8)

    val amount = decimal("amount")

    val timestamp = timestamp("last_update")
}

class ItemsTable: Table<Nothing>("items") {
    val id = int("id").primaryKey()

    val description = varchar("description")
}

class CustomersTable: Table<Nothing>("customers") {
    val id = int("id").primaryKey()

    val email = varchar("email")
}
