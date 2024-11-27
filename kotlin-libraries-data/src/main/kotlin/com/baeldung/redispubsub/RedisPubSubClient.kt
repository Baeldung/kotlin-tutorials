package com.baeldung.redispubsub

import com.google.gson.Gson
import io.lettuce.core.RedisClient
import io.lettuce.core.RedisURI
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands

class RedisPubSubClient {


    private val client: RedisClient = createRedisClient()
    private lateinit var connection: StatefulRedisConnection<String, String>

    private fun createRedisClient(): RedisClient {
        val redisUri = RedisURI.Builder.redis("localhost", 6379)
                .build()
        return RedisClient.create(redisUri)
    }

    fun createConnection() {
        connection = client.connect()
    }
    fun closeConnection() {
        connection.close()
        client.shutdown()
    }

    fun subscribeToChannel(channel: String) {
        val pubSubConnection: StatefulRedisPubSubConnection<String, String> = client.connectPubSub()
        pubSubConnection.addListener(MessageListener())

        val asyncCommands: RedisPubSubAsyncCommands<String, String> = pubSubConnection.async()
        asyncCommands.subscribe(channel)
    }

    fun publishMessage(channel: String, message: Message) {
        val syncCommands = connection.sync()
        val messageJson = Gson().toJson(message)
        syncCommands.publish(channel, messageJson)
        println("Message published: $messageJson")
    }


}