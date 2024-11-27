package com.baeldung.redispubsub

import redis.embedded.RedisServer

private val redisServer: RedisServer = RedisServer(6379)

fun main() {
    redisServer.start()

    val pubSubClient = RedisPubSubClient()
    val channel = "test-channel"
    val message = Message("Hello, Redis!")

    pubSubClient.createConnection()

    pubSubClient.subscribeToChannel(channel)

    pubSubClient.publishMessage(channel, message)

    pubSubClient.closeConnection()

    redisServer.stop()
}

