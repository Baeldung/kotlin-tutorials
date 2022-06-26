package com.baeldung.dataclass

fun main(args: Array<String>) {

    val task = Task(1001, "Replace Fuel Tank Filler Caps", 5)

    println(task.id)
    println(task.description)
    println(task.priority)

    task.priority = 4

    println(task.toString())

    val copyTask = task.copy(priority = 4)
    println(copyTask.toString())

    task.component1()
    task.component2()
    task.component3()

    val(id, description, priority) = task

    fun getTask() = task
    val (idf, descriptionf, priorityf) = getTask()
}