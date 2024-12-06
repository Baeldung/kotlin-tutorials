package com.baeldung.redispubsub

import io.lettuce.core.pubsub.RedisPubSubAdapter
import java.util.concurrent.CountDownLatch

class MessageListener : RedisPubSubAdapter<String, String>() {

    var latch: CountDownLatch = CountDownLatch(1)

    var messagesReceived: List<String> = emptyList()
    override fun message(channel: String?, message: String?) {
        println("Received message: $message from channel: $channel")
        messagesReceived = messagesReceived.plus(message!!)
        latch.countDown()
    }

}