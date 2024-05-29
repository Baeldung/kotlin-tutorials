package com.baeldung.jeekotlin.service

import com.baeldung.jeekotlin.entity.Student
import jakarta.ejb.Stateless
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

@Stateless
open class StudentService {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    open fun create(student: Student) = entityManager.persist(student)

    open fun read(id: Long): Student? = entityManager.find(Student::class.java, id)

    open fun update(student: Student) = entityManager.merge(student)

    open fun delete(id: Long) = entityManager.remove(read(id))
}