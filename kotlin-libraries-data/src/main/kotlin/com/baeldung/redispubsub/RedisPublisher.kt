package com.baeldung.redispubsub

class RedisPublisher {

    fun publishMessage(channel: String, message: Message) {
        RedisConnectionManager.redisCommands()?.publish(channel, message.content)
        println("Message published: $message")
    }
}