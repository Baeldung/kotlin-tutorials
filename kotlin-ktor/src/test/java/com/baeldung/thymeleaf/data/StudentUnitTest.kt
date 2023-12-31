package com.baeldung.thymeleaf.data

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StudentUnitTest {

    @Test
    fun `when fullName is called then should return firstName and lastName`(){
        val student = Student(
            id = "1",
            firstName = "John",
            lastName = "Doe",
            gradeList = ArrayList()
        )

        assertEquals("John Doe", student.fullName)
    }

    @Test
    fun `when hasAllGrades is called then should return true if all grades are filled`(){
        val student = Student(
            id = "1",
            firstName = "John",
            lastName = "Doe",
            gradeList = listOf(
                Grade(id = "1", subject = "Reading", value = "A"),
                Grade(id = "2", subject = "Writing", value = "A"),
            )
        )

        assertTrue(student.hasAllGrades)
    }

    @Test
    fun `when hasAllGrades is called then should return false if some grade value is null`(){
        val student = Student(
            id = "1",
            firstName = "John",
            lastName = "Doe",
            gradeList = listOf(
                Grade(id = "1", subject = "Reading", value = "A"),
                Grade(id = "2", subject = "Writing"),
            )
        )

        assertFalse(student.hasAllGrades)
    }

}