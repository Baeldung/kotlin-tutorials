package com.baeldung.functionalErrorHandling

import com.baeldung.functionalErrorHandling.ResultMonad.catch
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ResultMonadTest {

    private fun findOrder(id: Int): Result<Order> = Result.catch {
        if (id > 0) Order(id, 1) else throw Exception("Order Id must be a positive number")
    }

    private fun findCustomer(id: Int): Result<Customer> = Result.catch {
        if (id == 1) Customer(id, "john.doe@mycompany.com") else throw Exception("Cannot find any customer for id $id")
    }

    @Test
    fun whenCallingFindOrderWithPositiveId_thenSuccess() = with(ResultMonad) {
        val result = findOrder(1)
            .map(Order::customerId)
            .flatMap(::findCustomer)
            .map(Customer::email)

        Assertions.assertEquals("john.doe@mycompany.com", result.getOrNull())
    }

    @Test
    fun whenCallingFindOrderWithNegativeId_thenFailure() = with(ResultMonad) {
        val result = findOrder(-1)
            .map(Order::customerId)
            .flatMap(::findCustomer)
            .map(Customer::email)

        Assertions.assertTrue(result.isFailure)
    }

}
