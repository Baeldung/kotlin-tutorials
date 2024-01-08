package com.baeldung.thymeleaf.data

import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class DataHolderUnitTest {

    @Test
    fun `when getStudentList is called then should retrieve a list`(){
        val studentList = DataHolder.getStudentList()

        assertEquals(3, studentList.size)
    }

    @Test
    fun `when findStudentById is called  then should return a specific Student`(){
        val student = DataHolder.findStudentById("2")

        assertEquals("2", student.id)
        assertEquals("Mary", student.firstName)
        assertEquals("Johnson", student.lastName)
        assertNotNull(student.gradeList)
        assertEquals(4, student.gradeList.size)
    }

    @Test
    fun `when updateGrades is called  then should update grades for a specific Student`(){
        val parameters = ParametersBuilder()
            .apply {
                append("1", "A")
                append("2", "B")
                append("3", "C")
                append("4", "D")
            }.build()

        DataHolder.updateGrades("1", parameters)

        val student = DataHolder.findStudentById("1")
        assertNotNull(student.gradeList)
        assertEquals(GradeValue.A, student.gradeList.first { it.id == "1" }.gradeValue)
        assertEquals(GradeValue.B, student.gradeList.first { it.id == "2" }.gradeValue)
        assertEquals(GradeValue.C, student.gradeList.first { it.id == "3" }.gradeValue)
        assertEquals(GradeValue.D, student.gradeList.first { it.id == "4" }.gradeValue)
    }

}