package com.baeldung.crud.service

import com.baeldung.crud.model.TaskDTORequest
import com.baeldung.crud.model.TaskDTOResponse
import com.baeldung.crud.repositories.TaskEntity
import com.baeldung.crud.repositories.TaskRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class TaskService(var repository: TaskRepository) {

    fun createTask(newTask: TaskDTORequest): TaskDTOResponse {
        val save = repository.save(
            TaskEntity(
                id = null,
                name = newTask.name,
                description = newTask.description,
                done = newTask.done
            )
        )
        return TaskDTOResponse(id = save.id!!, name = save.name, description = save.description, done = save.done)
    }

    fun getTask(id: Long): TaskDTOResponse? {
        return repository.findById(id)
            .map { TaskDTOResponse(id = it.id!!, name = it.name, description = it.description, done = it.done) }
            .getOrNull()
    }

    fun deleteTask(id: Long) {
        repository.deleteById(id)
    }

    fun updateTask(id: Long, updatedTask: TaskDTORequest): TaskDTOResponse? {
        return repository.findById(id).map {
            val save = repository.save(
                TaskEntity(
                    id = it.id,
                    name = updatedTask.name,
                    description = updatedTask.description,
                    done = updatedTask.done
                )
            )
            TaskDTOResponse(id = save.id!!, name = save.name, description = save.description, done = save.done)
        }.orElseGet(null)
    }
}