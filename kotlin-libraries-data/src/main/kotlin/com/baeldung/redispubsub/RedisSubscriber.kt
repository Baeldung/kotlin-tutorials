package com.baeldung.redispubsub

class RedisSubscriber(private val messageListener: MessageListener) {

    fun subscribeToChannel(channel: String) {
        RedisConnectionManager.redisPubSubAsyncCommands(messageListener).subscribe(channel)
    }


}
