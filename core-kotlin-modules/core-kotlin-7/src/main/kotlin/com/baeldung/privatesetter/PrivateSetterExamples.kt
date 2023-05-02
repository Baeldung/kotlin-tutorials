package com.baeldung.privatesetter


fun main() {
    println("Private Setter Examples")
}

object PrivateSetterExamples {

//    fun myClassExample() {
//        val obj = MyClass()
//        obj.myProperty = "new value"
//        // Compilation fails with message:
//        // Cannot assign to 'myProperty': the setter is private in 'MyClass'
//    }

    fun bankAccountExampleSuccess(): Int {
        print("Bank Account Example: ")
        val bankAccount = BankAccount()
        bankAccount.deposit(100)
        bankAccount.withdraw(50)
        println("Bank Account balance is: " + bankAccount.balance)
        // Output: Bank Account balance is: 50
        return bankAccount.balance
    }

    fun bankAccountExampleException() {
        val bankAccount = BankAccount()
        bankAccount.deposit(100)
        bankAccount.withdraw(1000)
        // Throws: IllegalArgumentException: Withdraw Amount cannot be greater than balance
    }

//    fun bankAccountPropertyAccessError() {
//        val bankAccount = BankAccount()
//        bankAccount.balance -= 1000
//        // Compilation fails with message:
//        //
//    }

    fun loggingBankAccountExample(): Int {
        println("Logging Bank Account Example: ")
        val bankAccount = LoggingBankAccount()
        bankAccount.deposit(100)
        bankAccount.withdraw(50)
        println("Bank Account balance is: " + bankAccount.balance)
        return bankAccount.balance
    }

}

/**
 * Simple class that have a private setter for a property
 */
class MyClass {
    var myProperty: String = "default"
        private set
}