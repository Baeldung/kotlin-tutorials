package com.baeldung.sort

import org.junit.jupiter.api.Test
import java.util.Comparator.comparing
import kotlin.test.assertEquals

class SortWithMultipleFieldsUnitTest {

    private val students = listOf(
        Student(name = "C", age = 9),
        Student(name = "A", age = 11, country = "C1"),
        Student(name = "B", age = 10, country = "C2"),
        Student(name = "A", age = 10),
    )

    private val studentsSortedByNameAndAge = listOf(
        Student(name = "A", age = 10),
        Student(name = "A", age = 11, country = "C1"),
        Student(name = "B", age = 10, country = "C2"),
        Student(name = "C", age = 9),
    )

    @Test
    fun whenSortedWithSelectorsWithoutSpecificOrder_thenGetNewListsAsExpected() {
        assertEquals(
            studentsSortedByNameAndAge,
            students.sortedWith(compareBy({ it.name }, { it.age }))
        )
    }

    @Test
    fun whenSortedWithPropertiesWithoutSpecificOrder_thenGetNewListsAsExpected() {
        assertEquals(
            studentsSortedByNameAndAge,
            students.sortedWith(compareBy(Student::name, Student::age))
        )
    }

    @Test
    fun whenSortedWithComparableInterface_thenGetNewListsAsExpected() {
        assertEquals(
            studentsSortedByNameAndAge,
            students.sorted()
        )
    }

    @Test
    fun whenSortedWithMutableListsWithoutSpecificOrder_thenGetListSorted() {
        val mutableStudents = students.toMutableList()
        mutableStudents.sortWith(compareBy(Student::name, Student::age))
        assertEquals(
            studentsSortedByNameAndAge,
            mutableStudents
        )
    }

    @Test
    fun whenSortedWithSpecificOrder_thenGetNewListsAsExpected() {
        assertEquals(
            listOf(
                Student(name = "C", age = 9),
                Student(name = "B", age = 10, country = "C2"),
                Student(name = "A", age = 11, country = "C1"),
                Student(name = "A", age = 10),
            ),
            students.sortedWith(compareByDescending<Student> { it.name }.thenByDescending { it.age })
        )
    }

    @Test
    fun whenSortedWithNullValues_thenGetNewListsAsExpected() {
        assertEquals(
            listOf(
                Student(name = "A", age = 10),
                Student(name = "C", age = 9),
                Student(name = "A", age = 11, country = "C1"),
                Student(name = "B", age = 10, country = "C2"),
            ),
            students.sortedWith(compareBy<Student> { it.country }.thenBy { it.name })
        )
    }

    @Test
    fun whenSortedWithNullValuesAndComparator_thenGetNewListsAsExpected() {
        val defaultCountry = "C11"
        assertEquals(
            listOf(
                Student(name = "A", age = 11, country = "C1"),
                Student(name = "A", age = 10),
                Student(name = "C", age = 9),
                Student(name = "B", age = 10, country = "C2"),
            ),
            students.sortedWith(
                comparing<Student?, String?>(
                    { it.country },
                    { c1, c2 -> (c1 ?: defaultCountry).compareTo(c2 ?: defaultCountry) }
                ).thenComparing(
                    { it.age },
                    { a1, a2 -> (a1 % 10).compareTo(a2 % 10) }
                )
            )
        )
    }

    @Test
    fun whenSortedWithNullValuesLast_thenGetNewListsAsExpected() {
        assertEquals(
            listOf(
                Student(name = "A", age = 11, country = "C1"),
                Student(name = "B", age = 10, country = "C2"),
                Student(name = "A", age = 10),
                Student(name = "C", age = 9),
            ),
            students.sortedWith(compareBy<Student, String?>(nullsLast()) { it.country }.thenBy { it.name })
        )
    }

    @Test
    fun whenSortedWithNullValuesFirst_thenGetNewListsAsExpected() {
        assertEquals(
            listOf(
                Student(name = "A", age = 10),
                Student(name = "C", age = 9),
                Student(name = "B", age = 10, country = "C2"),
                Student(name = "A", age = 11, country = "C1"),
            ),
            students.sortedWith(compareBy<Student, String?>(nullsFirst(reverseOrder())) { it.country }.thenBy { it.name })
        )
    }

    data class Student(val name: String, val age: Int, val country: String? = null) : Comparable<Student> {
        override fun compareTo(other: Student): Int {
            return compareValuesBy(this, other, { it.name }, { it.age })
        }
    }
}