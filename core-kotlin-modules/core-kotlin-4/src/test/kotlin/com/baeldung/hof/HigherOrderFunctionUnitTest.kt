package com.baeldung.hof

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class HigherOrderFunctionUnitTest {

    @Test
    fun given_simple_lambda_then_wraps_it_around_with_actions() {
        performAction { println("Hello, world!") }
    }

    @Test
    fun given_supplier_lambda_then_measures_time_spent() {
        val sugar = supplyWithTiming { SuppliedType("Sugar", 1) }
        assertEquals("Sugar", sugar.name)
    }

    @Test
    fun given_interface_supplier_then_allows_verification() {
        val sugar = verifiedSupplier("Sugar", 1) { name, weight -> SuppliedType(name, weight) }
        assertEquals("Sugar", sugar.name)
    }

    @Test
    fun given_curry_function_then_allows_partial_application() {
        val plusFour = curry(4) { a, b -> a + b }
        assertEquals(6, plusFour(2))
    }
}
