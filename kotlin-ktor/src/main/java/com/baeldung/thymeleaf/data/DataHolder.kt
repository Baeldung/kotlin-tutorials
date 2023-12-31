package com.baeldung.thymeleaf.data

import io.ktor.http.*

object DataHolder {

    fun getStudentList() = studentsList

    fun findStudentById(id: String?) = getStudentList().first { student -> student.id == id }

    fun updateGrades(studentId: String?, parameters: Parameters) {
        findStudentById(studentId)
            .gradeList.forEach { grade ->
                grade.apply {
                    value = parameters[grade.id] ?: "F"
                }
            }
    }

    private val studentsList = listOf(
        Student(
            id = "1",
            firstName = "Michael",
            lastName = "Smith",
            gradeList = createGradeList()
        ),
        Student(
            id = "2",
            firstName = "Mary",
            lastName = "Johnson",
            gradeList = createGradeList()
        ),
        Student(
            id = "3",
            firstName = "John",
            lastName = "Doe",
            gradeList = createGradeList()
        ),
    )

    private fun createGradeList() = listOf(
        Grade(id = "1", subject = "Reading"),
        Grade(id = "2", subject = "Writing"),
        Grade(id = "3", subject = "Science"),
        Grade(id = "4", subject = "Mathematics"),
    )

}