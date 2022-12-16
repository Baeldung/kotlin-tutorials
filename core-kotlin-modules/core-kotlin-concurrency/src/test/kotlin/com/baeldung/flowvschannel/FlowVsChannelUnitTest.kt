package com.baeldung.flowvschannel

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.produceIn
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FlowVsChannelUnitTest {

    @Test
    fun when_collected_flow_multiple_time_then_return_same_values() = runBlocking {
        val coldStream = flow {
            for (i in 1..5) {
                delay(100)
                emit(i)
            }
        }

        val collect1 = buildString {
            coldStream.collect { append(it).append(", ") }
        }.removeSuffix(", ")

        val collect2 = buildString {
            coldStream.collect { append(it).append(", ") }
        }.removeSuffix(", ")
        assertEquals(collect1, "1, 2, 3, 4, 5")
        assertEquals(collect2, "1, 2, 3, 4, 5")
    }

    @Test
    fun should_pass_data_from_one_coroutine_to_another() = runBlocking {
        val channel = Channel<String>()
        val message = "message"

        launch { // coroutine #1
            channel.send(message)
        }
        val result = async { // coroutine #2
            channel.receive()
        }

        assertEquals(result.await(), message)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun when_conflated_channel_then_drop_first_emission() = runBlocking {
        val channel = Channel<Int>(capacity = Channel.CONFLATED)

        produce<Int> {
            for (i in 1..5) {
                delay(100L)
                channel.send(i)
            }
            channel.close()
        }
        val result = async {
            val str1 = StringBuilder()
            val str2 = StringBuilder()

            delay(250L)
            var a = channel.receive()
            str1.append(a).append(", ")

            // receive remaining items from the buffer
            while (!channel.isClosedForReceive) {
                a = channel.receive()
                str2.append(a).append(", ")
            }

            str1.removeSuffix(", ") to str2.removeSuffix(", ")
        }

        assertEquals(result.await(), "2" to "3, 4, 5")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun when_fixed_buffer_size_then_collect_awaiting_emission_to_second_result() = runBlocking {
        val channel = Channel<Int>(capacity = 2)

        produce<Int> {
            for (i in 1..5) {
                delay(100L)
                channel.send(i)
            }
            channel.close()
        }
        val result = async {
            val str1 = StringBuilder()
            val str2 = StringBuilder()

            delay(250L)
            var a = channel.receive()
            str1.append(a).append(", ")

            delay(250L)
            a = channel.receive()
            str1.append(a).append(", ")

            // receive remaining items from the buffer
            while (!channel.isClosedForReceive) {
                a = channel.receive()
                str2.append(a).append(", ")
            }

            str1.removeSuffix(", ") to str2.removeSuffix(", ")
        }

        assertEquals(result.await(), "1, 2" to "3, 4, 5")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun when_buffer_drop_oldest_then_receive_only_2_last_items() = runBlocking {
        val channel = Channel<Int>(onBufferOverflow = BufferOverflow.DROP_OLDEST)

        produce<Int> {
            for (i in 1..5) {
                delay(100L)
                channel.send(i)
            }
            channel.close()
        }
        val result = async {
            buildString {
                while (!channel.isClosedForReceive) {
                    delay(500L)
                    val a = channel.receive()
                    append(a).append(", ")
                }
            }.removeSuffix(", ")
        }

        assertEquals(result.await(), "4, 5")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun when_buffer_drop_latest_then_receive_only_first_item() = runBlocking {
        val channel = Channel<Int>(onBufferOverflow = BufferOverflow.DROP_LATEST)

        produce<Int> {
            for (i in 1..5) {
                delay(100L)
                channel.send(i)
            }
            channel.close()
        }
        val result = async {
            buildString {
                while (!channel.isClosedForReceive) {
                    delay(550L)
                    val a = channel.receive()
                    append(a).append(", ")
                }
            }.removeSuffix(", ")
        }

        assertEquals(result.await(), "1")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun when_use_produce_then_consumeEach_receives_all_values() = runBlocking {
        val channel = Channel<Int>()

        produce<Int> {
            for (i in 1..5) {
                channel.send(i)
            }
            channel.close()
        }
        val result = async {
            buildString {
                channel.consumeEach {
                    append(it).append(", ")
                }
            }.removeSuffix(", ")
        }

        assertEquals(result.await(), "1, 2, 3, 4, 5")
    }

    @OptIn(FlowPreview::class)
    @Test
    fun when_flow_produceIn_then_consume_all_values() = runBlocking {
        val channel = flow {
            for (i in 1..5) {
                delay(100)
                emit(i)
            }
        }.buffer(capacity = 2, onBufferOverflow = BufferOverflow.SUSPEND)
            .produceIn(this)

        val result = async {
            buildString {
                channel.consumeEach {
                    append(it).append(", ")
                }
            }.removeSuffix(", ")
        }

        assertEquals(result.await(), "1, 2, 3, 4, 5")
    }
}
