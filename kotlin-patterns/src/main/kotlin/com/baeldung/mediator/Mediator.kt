package com.baeldung.mediator


// Mediator interface
interface ChatMediator {
    fun sendMessage(message: String, user: User)
}
// Concrete Mediator
class ChatMediatorImpl : ChatMediator {
    private val users = mutableListOf<User>()
    override fun sendMessage(message: String, user: User) {
        // Broadcast the message to all users except the sender
        users.filter { it != user }.forEach { it.receiveMessage(message) }
    }
    fun addUser(user: User) {
        users.add(user)
    }
}


// Colleague interface
interface User {
    fun sendMessage(message: String)
    fun receiveMessage(message: String)
}
// Concrete Colleague
class ChatUser(private val name: String, private val mediator: ChatMediator) : User {
    override fun sendMessage(message: String) {
        println("$name sends: $message")
        mediator.sendMessage(message, this)
    }
    override fun receiveMessage(message: String) {
        println("$name receives: $message")
    }
}