package com.baeldung.ktorm

import org.ktorm.entity.Entity
import org.ktorm.schema.*
import java.math.BigDecimal
import java.time.Instant

interface Item : Entity<Item> {
    companion object : Entity.Factory<Item>()

    val id: Int

    var description: String
}

interface Customer : Entity<Customer> {
    companion object : Entity.Factory<Customer>()

    val id: Int

    var email: String
}

interface Order : Entity<Order> {
    companion object : Entity.Factory<Order>()

    val id: Int

    var item: Item

    var customer: Customer

    var card: String

    var amount: BigDecimal

    var timestamp: Instant
}

object Orders : Table<Order>("orders") {
    val id = int("id").primaryKey().bindTo { it.id }

    val itemId = int("item_id").references(Items) { it.item }

    val customerId = int("customer_id").references(Customers) { it.customer }

    val card = base64("free_text_card", Charsets.UTF_8).bindTo { it.card }

    val amount = decimal("amount").bindTo { it.amount }

    val timestamp = timestamp("last_update").bindTo { it.timestamp }
}

object Items : Table<Item>("items") {
    val id = int("id").primaryKey().bindTo { it.id }

    val description = varchar("description").bindTo { it.description }
}

object Customers : Table<Customer>("customers") {
    val id = int("id").primaryKey().bindTo { it.id }

    val email = varchar("email").bindTo { it.email }
}


