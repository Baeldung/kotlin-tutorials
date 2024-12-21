package com.baeldung.redispubsub

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands

object RedisConnectionManager: AutoCloseable {
    private val redisClient: RedisClient = RedisClient.create("redis://localhost:6379")
    private val connection: StatefulRedisConnection<String, String> = redisClient.connect()

    override fun close() {
        connection.close()
        redisClient.shutdown()
    }

    fun redisSyncCommands(): RedisCommands<String, String>? {
        return connection.sync()
    }

    fun redisPubSubAsyncCommands(messageListener: MessageListener): RedisPubSubAsyncCommands<String, String> {
        val pubSubConnection = redisClient.connectPubSub()
        pubSubConnection.addListener(messageListener)
        return pubSubConnection.async()
    }
}
