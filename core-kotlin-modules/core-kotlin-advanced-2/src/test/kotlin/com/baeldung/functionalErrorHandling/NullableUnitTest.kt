package com.baeldung.functionalErrorHandling


import com.baeldung.functionalErrorHandling.Nullable.nullable
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import kotlin.test.assertNull

class NullableUnitTest {

    private fun findOrder(id: Int): Order? = nullable {
        if(id > 0) Order(id, 1) else throw Exception("Order Id must a positive number")
    }

    @Test
    fun whenCallingFindOrderWithPositiveId_thenSuccess() {
        assertNotNull(findOrder(1))
    }

    @Test
    fun whenCallingFindOrderWithNegativeId_thenFailure() {
        assertNull(findOrder(-1))
    }
}