package com.baeldung.redispubsub

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection

object RedisConnectionManager: AutoCloseable {
    val redisClient: RedisClient = RedisClient.create("redis://localhost:6379")
    val connection: StatefulRedisConnection<String, String> = redisClient.connect()

    override fun close() {
        connection.close()
        redisClient.shutdown()
    }
}
