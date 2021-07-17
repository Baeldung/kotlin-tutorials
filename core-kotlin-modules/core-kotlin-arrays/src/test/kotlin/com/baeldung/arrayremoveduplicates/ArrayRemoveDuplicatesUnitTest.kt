package com.baeldung.arrayremoveduplicates

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ArrayRemoveDuplicatesUnitTest {

    data class Employee(val name: String, val id: String)

    @Test
    fun `Given an array of strings, When called toMutableSet, Then return unique values`() {
        val data = arrayOf("pie", "tie", "lie", "pie")
        assertEquals(4, data.size)
        val uniqueData = data.toMutableSet()
        assertEquals(3, uniqueData.size)
        assertTrue(uniqueData.contains("pie"))
        assertTrue(uniqueData.contains("tie"))
        assertTrue(uniqueData.contains("lie"))
    }

    @Test
    fun `Given an array of strings, When called distinct, Then return unique values`() {
        val data = arrayOf("pie", "tie", "lie", "pie")
        assertEquals(4, data.size)
        val uniqueData = data.distinct()
        assertEquals(3, uniqueData.size)
        assertEquals("pie", uniqueData[0])
        assertEquals("tie", uniqueData[1])
        assertEquals("lie", uniqueData[2])
    }

    @Test
    fun `Given an array of employees, When distinctBy id, Then return unique employees by id`() {
        val emp1 = Employee("Jimmy", "1")
        val emp2 = Employee("James", "2")
        val emp3 = Employee("Jimmy", "3")
        val employees = arrayOf(emp1, emp2, emp1, emp3)
        val uniqueEmployees = employees.distinctBy { it.id }
        assertEquals(3, uniqueEmployees.size)
        assertEquals("Jimmy", uniqueEmployees.get(0).name)
        assertEquals("1", uniqueEmployees.get(0).id)
        assertEquals("James", uniqueEmployees.get(1).name)
        assertEquals("2", uniqueEmployees.get(1).id)
        assertEquals("Jimmy", uniqueEmployees.get(2).name)
        assertEquals("3", uniqueEmployees.get(2).id)
    }

    @Test
    fun `Given an array of employees, When distinctBy name, Then return unique employees by name`() {
        val emp1 = Employee("John", "1")
        val emp2 = Employee("John", "2")
        val employees = arrayOf(emp1, emp2, emp1)

        val employeesWithUniqueNames = employees.distinctBy { it.name }
        assertEquals(1, employeesWithUniqueNames.size)
        assertEquals("John", employeesWithUniqueNames.get(0).name)
        assertEquals("1", employeesWithUniqueNames.get(0).id)
    }
}