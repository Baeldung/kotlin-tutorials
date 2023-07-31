package com.baeldung.transformation

data class Employee (
    val empId: Int,
    val firstName: String,
    val lastName: String,
    val monthlySalary: Double) {
    fun name(): String {
        return "$firstName $lastName"
    }
}

data class Organization (
    val orgId: Int,
    val employees: List<Employee>
)