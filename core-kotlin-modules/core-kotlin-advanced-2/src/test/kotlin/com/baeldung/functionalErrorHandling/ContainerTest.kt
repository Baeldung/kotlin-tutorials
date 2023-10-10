package com.baeldung.functionalErrorHandling

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class ContainerTest {

    private fun findOrder(id: Int): Container<Throwable, Order> = Container.catch {
        if (id > 0) Order(id, 1) else throw Exception("Order Id must be a positive number")
    }

    private fun findCustomer(id: Int): Container<Throwable, Customer> = Container.catch {
        if (id == 1) Customer(id, "john.doe@mycompany.com") else throw Exception("Cannot find any customer for id $id")
    }

    @Test
    fun whenCallingFindOrderWithPositiveId_thenSuccess() {
        assertIs<Container.Success<String>>(findOrder(1))
    }

    @Test
    fun whenCallingFindOrderWithNegativeId_thenFailure() {
        assertIs<Container.Failure<Throwable>>(findOrder(-1))
    }

    @Test
    fun whenExtractingOrderIdWithPositiveId_thenSuccess() {
        val id = when (val container = findOrder(1)) {
            is Container.Success -> container.value.id
            is Container.Failure -> null
        }

        assertNotNull(id)
    }

    @Test
    fun whenExtractingCustomerEmailWithPositiveOrderId_thenSuccess() {
        val customerEmail = when (val container = findOrder(1)) {

            is Container.Success -> when (val customerContainer = findCustomer(container.value.customerId)) {
                is Container.Success -> customerContainer.value.email
                is Container.Failure -> null
            }

            is Container.Failure -> null
        }

        assertEquals("john.doe@mycompany.com", customerEmail)
    }
}