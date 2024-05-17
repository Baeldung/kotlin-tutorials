package com.baeldung.iterate.components

open class Employee() {
    var employeeId: Int = 0
    var salary: Double = 0.0

    val currentSalary: Double
        get() = salary

    val Employee.isSenior: Boolean
        get() = salary >= 1000.0

    fun Employee.isPromoted(): Boolean {
        return salary >= 2000.0
    }
}