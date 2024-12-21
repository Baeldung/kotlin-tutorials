package com.baeldung.redispubsub

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection

object RedisConnectionManager: AutoCloseable {
    private val redisClient: RedisClient = RedisClient.create("redis://localhost:6379")
    private val connection: StatefulRedisConnection<String, String> = redisClient.connect()

    override fun close() {
        connection.close()
        redisClient.shutdown()
    }

    fun statefulRedisPubSubConnection(): StatefulRedisPubSubConnection<String, String> {
        return redisClient.connectPubSub()
    }

    fun redisCommands(): RedisCommands<String, String>? {
        return connection.sync()
    }
}
