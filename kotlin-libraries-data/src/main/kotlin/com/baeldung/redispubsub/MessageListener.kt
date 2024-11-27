package com.baeldung.redispubsub

import io.lettuce.core.pubsub.RedisPubSubAdapter

class MessageListener : RedisPubSubAdapter<String, String>() {

    override fun message(channel: String?, message: String?) {
        println("Received message: $message from channel: $channel")
    }

}