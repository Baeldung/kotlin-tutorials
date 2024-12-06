package com.baeldung.redispubsub

import io.lettuce.core.pubsub.StatefulRedisPubSubConnection
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands

class RedisSubscriber(private val messageListener: MessageListener) {

    fun subscribeToChannel(channel: String) {
        val pubSubConnection: StatefulRedisPubSubConnection<String, String> = RedisConnectionManager.redisClient.connectPubSub()
        pubSubConnection.addListener(messageListener)
        val asyncCommands: RedisPubSubAsyncCommands<String, String> = pubSubConnection.async()

        asyncCommands.subscribe(channel)
    }
}
