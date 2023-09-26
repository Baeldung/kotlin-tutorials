package com.baeldung.functionalErrorHandling

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.raise.either
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ArrowEitherTest {

    object ArrowEither {

        fun findOrder(id: Int): Either<Throwable, Order> = Either.catch {
            if (id > 0) Order(id, 1) else throw Exception("Order Id must be a positive number")
        }

        fun findCustomer(id: Int): Either<Throwable, Customer> = Either.catch {
            if (id == 1) Customer(id, "john.doe@mycompany.com") else throw Exception("Cannot find any customer for id $id")
        }

        object WithDomainError {

            fun findOrder(id: Int): Either<DomainError, Order> = either {
                if (id < 0) raise(DomainError.OrderNotFound(id))
                Order(id, 1)
            }

            fun findCustomer(id: Int): Either<DomainError, Customer> = either {
                if (id != 1) raise(DomainError.CustomerNotFound(id))
                Customer(id, "john.doe@mycompany.com")
            }
        }

        fun findOrderWithDomainError(id: Int): Either<DomainError, Order> = findOrder(id)
            .mapLeft {
                DomainError.OrderNotFound(id)
            }

        fun findCustomerWithDomainError(id: Int): Either<DomainError, Customer> = findCustomer(id)
            .mapLeft {
                DomainError.CustomerNotFound(id)
            }
    }

    @Test
    fun whenCallingFindOrderWithPositiveId_thenSuccess() {

        val either = ArrowEither.findOrder(1)
            .map(Order::customerId)
            .flatMap(ArrowEither::findCustomer)
            .map(Customer::email)

        assertEquals("john.doe@mycompany.com", either.getOrNull())
    }

    @Test
    fun whenCallingFindOrderWithNegativeId_thenFailure() {
        val either = ArrowEither.findOrder(-1)
            .map(Order::customerId)
            .flatMap(ArrowEither::findCustomer)
            .map(Customer::email)

        assertTrue(either.isLeft())
    }

    @Test
    fun whenCallingFindOrderWithNegativeIdOnDomainVariant_thenFailure() {

        val either = ArrowEither.WithDomainError.findOrder(-1)
            .map(Order::customerId)
            .flatMap(ArrowEither.WithDomainError::findCustomer)
            .map(Customer::email)

        assertTrue(either.leftOrNull() is DomainError.OrderNotFound)
    }

    @Test
    fun whenCallingFindOrderWithDomainErrorOnNegativeId_thenFailure() {

        val either = ArrowEither.findOrderWithDomainError(-1)
            .map(Order::customerId)
            .flatMap(ArrowEither.WithDomainError::findCustomer)
            .map(Customer::email)

        assertTrue(either.leftOrNull() is DomainError.OrderNotFound)
    }

    @Test
    fun whenCallingFindCustomerWithDomainErrorOnNegativeId_thenFailure() {
        val either = ArrowEither.findCustomerWithDomainError(-1)
            .map(Customer::email)

        assertTrue(either.leftOrNull() is DomainError.CustomerNotFound)
    }



}