package com.baeldung.solid

import java.math.BigDecimal
import java.util.UUID

data class Department(
    val title: String,
    val employeeCount: Int
)

fun mapToString(departments: List<Department>) =
    departments.map { it.toString() }

fun mapToEmployeeCount(departments: List<Department>) =
    departments.map { it.employeeCount }

class TraditionalEmployeeService(private val employeeRepository: EmployeeRepository) {
    fun handleRaise(id: UUID, raise: BigDecimal) =
        employeeRepository.findById(id)
            .let { employeeRepository.raiseSalary(it, raise) }
            .let { employeeRepository.upsert(it) }

    fun handleTitleChange(id: UUID, newTitle: String) =
        employeeRepository.findById(id)
            .copy(title = newTitle)
            .let { employeeRepository.upsert(it) }
}

// But if there are many fields like that, finding and saving gets repetitive
class FunctionalEmployeeService(private val employeeRepository: EmployeeRepository) {
    private fun handleChange(id: UUID, change: PureEmployee.() -> PureEmployee) {
        employeeRepository.findById(id)
            .change()
            .let { employeeRepository.upsert(it) }
    }

    fun handleRaise(id: UUID, raise: BigDecimal) =
        handleChange(id) { copy(salary = salary + raise) }

    fun handleTitleChange(id: UUID, newTitle: String) =
        handleChange(id) { copy(title = newTitle) }
}