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
                delay(100L)
                emit(i)
            }
        }

        val collect1 = buildString {
            coldStream.collect { append(it).append(", ") }
        }.removeSuffix(", ")

        val collect2 = buildString {
            coldStream.collect { append(it).append(", ") }
        }.removeSuffix(", ")
        assertEquals("1, 2, 3, 4, 5", collect1)
        assertEquals("1, 2, 3, 4, 5", collect2)
    }

    @Test
    fun should_pass_data_from_one_coroutine_to_another() = runBlocking {
        val channel = Channel<Int>()

        launch { // coroutine #1
            for (i in 1..5) {
                delay(100L)
                channel.send(i)
            }
            channel.close()
        }
        val result = async { // coroutine #2
            buildString {
                channel.consumeEach {
                    append(it).append(", ")
                }
            }.removeSuffix(", ")
        }

        assertEquals("1, 2, 3, 4, 5", result.await())
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
        }.buffer(
                capacity = 2,
                onBufferOverflow = BufferOverflow.SUSPEND
        ).produceIn(this)

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
