package com.baeldung.railwayorientedprogramming

data class Customer(val name: String, val emailAddress: String)

object ROP {
    fun createAndSave(args: Array<String>): Result<String> {
        return Success(args.toList()) then ::parse then ::validateCustomerName then ::validateCustomerEmail then ::saveCustomer otherwise ::error
    }

    private fun parse(inp: List<String>): Result<Customer> {
        return Success(Customer(inp.get(0), inp.get(1)))
    }

    private fun validateCustomerName(customer: Customer): Result<Customer> {
        if (customer.name.length < 10) {
            return Failure("Name validation failed; name length must be greater than 10 characters")
        } else {
            return Success(customer)
        }
    }

    private fun validateCustomerEmail(customer: Customer): Result<Customer> {
        if (customer.emailAddress.contains("@")) {
            return Success(customer)
        } else {
            return Failure("Email validation failed; email must contain the '@' symbol")
        }
    }

    private fun saveCustomer(customer: Customer): Result<String> {
        return Success("Customer successfully saved: " + customer)
    }

    private fun error(message: String): Failure<String> {
        return Failure("Error: ${message}")
    }

}
