package com.baeldung.dataclass

fun main() {

    val task = Task(1001, "Replace Fuel Tank Filler Caps", 5)

    println(task.id)
    println(task.description)
    println(task.priority)

    task.priority = 4

    println(task.toString())

    val secondTask = Task(1002, 2)
    println(secondTask.id) // 1002
    println(secondTask.description) // Anonymous task with priority 2

    val copyTask = task.copy(priority = 4)
    println(copyTask.toString())

    task.component1()
    task.component2()
    task.component3()

    val(_, _, _) = task

    fun getTask() = task
    val (_, _, _) = getTask()
}