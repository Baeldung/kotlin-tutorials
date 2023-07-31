package com.baeldung.transformation

import org.junit.Before
import org.junit.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

class CustomObjectTransformationKtTest {

    val namesList = listOf(
        Pair("John", "Doe"),
        Pair("Jill", "Fowler"),
        Pair("Jack", "Smith"),
        Pair("John", "Doe"),
    )

    val empList = listOf(
        Employee(1, "John", "Doe", 1200.0),
        Employee(2, "Jill", "Fowler", 1200.0),
        Employee(3, "Jack", "Smith", 1200.0),
        Employee(4, "John", "Doe", 1200.0),
    )

    val empByOrgList = listOf(
        Organization(
            100,
            listOf(
                Employee(1, "John", "Doe", 1200.0),
                Employee(2, "Jill", "Fowler", 1200.0)
            )
        ),
        Organization(
            200,
            listOf(
                Employee(3, "Jack", "Smith", 1200.0),
                Employee(4, "John", "Doe", 1200.0)
            )
        ),
    )

    @Before
    fun setUp() {
        namesList.mapIndexed { index, item ->
            Employee(index + 1, item.first, item.second, Random.nextDouble(from = 1000.0, until = 5000.0))
        }
    }

    @Test
    fun givenListOfEmployeeObjects_whenTransformed_returnListOfEmployeeName() {
        val empNameList = empList.map { it.name() }
        assertNotNull(empNameList)
        empNameList.forEach {
            assertNotNull(it)
            assert(it is String)
        }
    }

    @Test
    fun givenListOfObjectsWithNulls_whenTransformed_filterNullsAndReturnListOfStrings() {
        val countries = listOf("US", "India", "Australia", null, "Ethiopia", "Brazil", null, "Romania")
        val nonNullCountries = countries.mapNotNull { it?.uppercase() }
        assertNotNull(countries)
        val nullList = nonNullCountries.filter { it == null }
        assertEquals(0, nullList.size)
    }

    @Test
    fun givenListOfEmployeeObjects_whenTransformedUsingForLoop_returnListOfEmployeeName() {
        val empNameList = mutableListOf<String>()
        for (emp in empList) {
            empNameList.add(emp.name())
        }

        assertNotNull(empNameList)
        empNameList.forEach {
            assertNotNull(it)
            assert(it is String)
        }
    }

    @Test
    fun givenListOfEmployeeObjects_whenTransformedUsingIndexBasedForLoop_returnListOfEmployeeName() {
        val empNameList = mutableListOf<String>()
        for (i in empList.indices) {
            empNameList.add(empList[i].name())
        }
        assertNotNull(empNameList)
        empNameList.forEach {
            assertNotNull(it)
            assert(it is String)
        }

        // Alternative index based solution
        val anotherEmpNameList = mutableListOf<String>()
        for ((i, emp) in empList.withIndex()) {
            anotherEmpNameList.add(empList[i].name())
            // Or
            // anotherEmpNameList.add(emp.name())
        }
    }

    @Test
    fun givenListOfEmployeeObjects_whenTransformedUsingRangeBasedForLoop_returnListOfEmployeeName() {
        val empNameList = mutableListOf<String>()
        for (i in 0 until empList.size) {
            empNameList.add(empList[i].name())
        }

        assertNotNull(empNameList)
        empNameList.forEach {
            assertNotNull(it)
            assert(it is String)
        }
    }

    @Test
    fun givenListOfEmployeeObjects_whenTransformedUsingForEachLoop_returnListOfEmployeeName() {
        val empNameList = mutableListOf<String>()
        empList.forEach { emp ->
            empNameList.add(emp.name())
        }
        assertNotNull(empNameList)
        empNameList.forEach {
            assertNotNull(it)
            assert(it is String)
        }
    }

    @Test
    fun givenListOfEmployeeObjects_whenTransformedUsingflatMap_returnListOfEmployeeName() {
        val empNameList = empByOrgList.flatMap { it.employees }.map { it.name() }
        assertNotNull(empNameList)
        empNameList.forEach {
            assertNotNull(it)
            assert(it is String)
        }
    }
}