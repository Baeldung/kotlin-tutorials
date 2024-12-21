package com.baeldung.redispubsub

import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands

class RedisSubscriber(private val messageListener: MessageListener) {

    fun subscribeToChannel(channel: String) {
        val pubSubConnection = RedisConnectionManager.statefulRedisPubSubConnection()
        pubSubConnection.addListener(messageListener)
        val asyncCommands: RedisPubSubAsyncCommands<String, String> = pubSubConnection.async()

        asyncCommands.subscribe(channel)
    }
}
