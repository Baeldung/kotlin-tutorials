package com.baeldung.redispubsub

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import redis.embedded.RedisServer
import java.util.concurrent.TimeUnit

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RedisSubscriberUnitTest {

    val messageListener = MessageListener()
    val redisSubscriber = RedisSubscriber(messageListener)
    val redisPublisher = RedisPublisher()
    val channel = "channel"
    val message = Message("Hello, Redis!")

    val redisServer = RedisServer(6379)

    @BeforeAll
    fun setUp() {
        redisServer.start()
    }

    @AfterAll
    fun tearDown() {
        RedisConnectionManager.close()
        redisServer.stop()
    }

    @Test
    fun givenMessageListener_whenMessagePublished_thenMessageReceived() {
        redisSubscriber.subscribeToChannel(channel)
        redisPublisher.publishMessage(channel, message)
        messageListener.latch.await(500, TimeUnit.MILLISECONDS)
        assertEquals(message.content, messageListener.messagesReceived.get(0))
    }

}