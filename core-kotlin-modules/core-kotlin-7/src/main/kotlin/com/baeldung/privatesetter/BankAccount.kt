package com.baeldung.privatesetter

/**
 * Sample class that have a private setter for a property
 * to demonstrate how private setters can help in keeping
 * class properties consistent.
 */
class BankAccount {

    var balance: Int = 0
        private set

    fun deposit(amount: Int) {
        require(amount > 0) { "Deposit Amount cannot be negative or zero" }
        balance += amount
    }

    fun withdraw(amount: Int) {
        require(amount > 0) { "Withdraw Amount cannot be negative or zero" }
        require(amount <= balance) { "Withdraw Amount cannot be greater than balance" }
        balance -= amount
    }

}

/**
 * Sample class that have a private setter for a property
 * to demonstrate how private setters can help in keeping
 * class properties consistent and execute custom logic
 * when the property is changed.
 */
class LoggingBankAccount {
    var balance: Int = 0
        private set(value) {
            println("Balance changed from $field to $value")
            field = value
        }

    fun deposit(amount: Int) {
        require(amount > 0) { "Deposit Amount cannot be negative or zero" }
        balance += amount
    }

    fun withdraw(amount: Int) {
        require(amount > 0) { "Withdraw Amount cannot be negative or zero" }
        require(amount <= balance) { "Withdraw Amount cannot be greater than balance" }
        balance -= amount
    }
}