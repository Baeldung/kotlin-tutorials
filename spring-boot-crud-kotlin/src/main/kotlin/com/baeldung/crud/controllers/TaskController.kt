package com.baeldung.crud.controllers

import com.baeldung.crud.model.TaskDTORequest
import com.baeldung.crud.model.TaskDTOResponse
import com.baeldung.crud.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/tasks")
class TaskController(var taskService: TaskService) {

    @PostMapping("/create")
    fun createTask(@RequestBody newTask: TaskDTORequest): TaskDTOResponse {
        return taskService.createTask(newTask)
    }

    @GetMapping("/{id}")
    fun getTask(@PathVariable id: Long): TaskDTOResponse {
        return taskService.getTask(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found")
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long) {
        taskService.deleteTask(id)
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody updatedTask: TaskDTORequest): TaskDTOResponse {
        return taskService.updateTask(id, updatedTask)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found")
    }


}
