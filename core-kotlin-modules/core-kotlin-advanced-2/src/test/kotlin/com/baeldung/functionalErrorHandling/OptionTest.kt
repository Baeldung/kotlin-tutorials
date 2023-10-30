package com.baeldung.functionalErrorHandling

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import org.junit.jupiter.api.Test
import kotlin.test.assertIs

class OptionTest {

    private fun findOrder(id: Int): Option<Order> = Option.catch {
        if (id > 0) Order(id, 1) else throw Exception("Order Id must a positive number")
    }

    @Test
    fun whenCallingFindOrderWithPositiveId_thenSuccess() {
        assertIs<Some<String>>(findOrder(1))
    }

    @Test
    fun whenCallingFindOrderWithNegativeId_thenFailure() {
        assertIs<None>(findOrder(-1))
    }
}