package com.baeldung.redispubsub

class RedisPublisher {

    fun publishMessage(channel: String, message: Message) {
        val connection = RedisConnectionManager.connection
        val syncCommands = connection.sync()
        syncCommands.publish(channel, message.content)
        println("Message published: $message")
    }
}