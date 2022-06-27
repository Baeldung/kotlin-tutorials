package com.baeldung.solid

import java.math.BigDecimal
import java.util.UUID
import javax.sql.DataSource

data class Employee(
    val id: UUID,
    val firstName: String,
    val secondName: String,
    val title: String,
    val salary: BigDecimal
) {
    fun save() {
        TODO("Save Employee to the database")
    }

    companion object {
        fun loadFromDb(id: UUID): Employee {
            TODO("Go to DB and create an Employee object")
        }
    }
}

data class PureEmployee(
    val id: UUID,
    val firstName: String,
    val secondName: String,
    val title: String,
    val salary: BigDecimal
)

class EmployeeRepository(dataSource: DataSource) {
    fun upsert(employee: PureEmployee) {
        TODO("Save Employee to the database")
    }

    fun findById(id: UUID): PureEmployee {
        TODO("Go to DB and create an Employee object")
    }

    fun raiseSalary(employee: PureEmployee, raise: BigDecimal): PureEmployee =
        employee.copy(salary = employee.salary + raise)
}

val service = EmployeeRepository(createDataSource())

fun handleSalaryRaise(employee: PureEmployee, raise: BigDecimal) {
service.raiseSalary(employee, raise)
    .let { service.upsert(it) }
}

internal fun createDataSource(): DataSource = TODO("Provide a datasource")