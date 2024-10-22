package com.baeldung.dataclass

data class Task(var id: Int, var description: String, var priority: Int) {
    constructor(id: Int, priority: Int) : this(id, "anonymous task with priority $priority", priority)
}
