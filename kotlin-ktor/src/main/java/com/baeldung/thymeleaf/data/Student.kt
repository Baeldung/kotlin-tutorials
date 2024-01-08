package com.baeldung.thymeleaf.data

data class Student(
    val id: String,
    val firstName: String,
    val lastName: String,
    val gradeList: List<Grade>,
) {
    val fullName: String
        get() = "$firstName $lastName"

    val hasAllGrades: Boolean
        get() = gradeList.firstOrNull { grade -> grade.gradeValue == GradeValue.EMPTY} == null
}
