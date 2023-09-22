package com.baeldung.transformation

import org.junit.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

class CustomObjectTransformationKtTest {

    private val empList = listOf(
        Employee(1,"John", "Doe", Random.nextDouble(1000.0, 5000.0)),
        Employee(2,"Jill", "Fowler", Random.nextDouble(1000.0, 5000.0)),
        Employee(3,"Jack", "Smith", Random.nextDouble(1000.0, 5000.0)),
        Employee(4,"John", "Doe", Random.nextDouble(1000.0, 5000.0)),
    )

    private val empByOrgList = listOf(
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

    private val expectedEmpNames = listOf("John Doe", "Jill Fowler", "Jack Smith", "John Doe")

    @Test
    fun givenListOfEmployeeObjects_whenTransformedUsingMap_returnListOfEmployeeName() {
        val empNameList = empList.map { it.name() }
        assertTrue(empNameList.isNotEmpty())
        assertTrue(empNameList == expectedEmpNames)
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
    fun givenListOfObjectsWithNulls_whenTransformedUsingMapNotNull_filterNullsAndReturnListOfStrings() {
        val empListWithNulls = listOf(*empList.toTypedArray(), null)
        val empNameList = empListWithNulls.mapNotNull { it?.name() }
        assertTrue(empNameList.isNotEmpty())
        assertTrue(empNameList == expectedEmpNames)
        empNameList.forEach {
            assertNotNull(it)
            assert(it is String)
        }
    }

    @Test
    fun givenListOfObjectsWithNulls_whenTransformedUsingMapTo_filterNullsAndReturnListOfStrings() {
        val empNameList = mutableListOf<String>()
        empList.mapTo(empNameList) { it.name() }
        assertTrue(empNameList.isNotEmpty())
        assertTrue(empNameList == expectedEmpNames)
        empNameList.forEach {
            assertNotNull(it)
            assert(it is String)
        }
    }

    @Test
    fun givenListOfObjectsWithNulls_whenTransformedUsingMapIndexed_filterNullsAndReturnListOfStrings() {
        val empNameList = empList.mapIndexed { index, item ->
            item.name()
        }
        assertTrue(empNameList.isNotEmpty())
        assertTrue(empNameList == expectedEmpNames)
        empNameList.forEach {
            assertNotNull(it)
        }
    }

    @Test
    fun givenListOfEmployeeObjects_whenTransformedUsingForLoop_returnListOfEmployeeName() {
        val empNameList = mutableListOf<String>()
        for (emp in empList) {
            empNameList.add(emp.name())
        }
        assertTrue(empNameList.isNotEmpty())
        assertTrue(empNameList == expectedEmpNames)
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
        assertTrue(empNameList.isNotEmpty())
        assertTrue(empNameList == expectedEmpNames)
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
        assertTrue(anotherEmpNameList.isNotEmpty())
        assertTrue(anotherEmpNameList == expectedEmpNames)
        anotherEmpNameList.forEach {
            assertNotNull(it)
            assert(it is String)
        }
    }

    @Test
    fun givenListOfEmployeeObjects_whenTransformedUsingRangeBasedForLoop_returnListOfEmployeeName() {
        val empNameList = mutableListOf<String>()
        for (i in 0 until empList.size) {
            empNameList.add(empList[i].name())
        }
        assertTrue(empNameList.isNotEmpty())
        assertTrue(empNameList == expectedEmpNames)
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
        assertTrue(empNameList.isNotEmpty())
        assertTrue(empNameList == expectedEmpNames)
        empNameList.forEach {
            assertNotNull(it)
            assert(it is String)
        }
    }

    @Test
    fun givenListOfEmployeeObjects_whenTransformedUsingflatMap_returnListOfEmployeeName() {
        val empNameList = empByOrgList
            .flatMap { it.employees }
            .map { it.name() }
        assertTrue(empNameList.isNotEmpty())
        assertTrue(empNameList == expectedEmpNames)
        empNameList.forEach {
            assertNotNull(it)
            assert(it is String)
        }
    }
}