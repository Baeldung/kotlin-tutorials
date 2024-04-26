package com.baeldung.railwayorientedprogramming

data class Customer(val name: String, val emailAddress: String)

object ROP {
    fun createAndSave(args: Array<String>): Result<String> {
        return validateName(args.toList()) then ::validateEmail then ::createCustomerDAO then ::save
    }

    fun validateName(inp: List<String>): Result<List<String>> {
        if(inp.get(0).length < 10) {
            return Failure("Name validation failed. Name must be greater than 10 characters.")
        }else{
            return Success(inp)
        }
    }

    fun validateEmail(inp: List<String>): Result<List<String>> {
        if(inp.get(1).contains("@")) {
            return Success(inp)
        }else{
            return Failure("Email validation failed. Email must contain '@' symbol.")
        }
    }

    fun createCustomerDAO(inp: List<String>): Result<Customer> = Success(Customer(inp.get(0), inp.get(1)))

    fun save(customer: Customer): Result<String> { return Success("Customer successfully saved: " + customer) }
}
