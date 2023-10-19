package com.baeldung.functionalErrorHandling

data class Order(val id: Int, val customerId: Int)

data class Customer(val id: Int, val email: String)

sealed class DomainError {
    data class OrderNotFound(val id: Int): DomainError()

    data class CustomerNotFound(val id: Int): DomainError()
}