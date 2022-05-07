package com.baeldung.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.time.Month
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class ExamplesUnitTest {
    
    @Test
    fun when_collected_flow_then_yields_all_values() = runBlocking {
        val inscription = buildString {
            listOf("mene", "mene", "tekel", "upharsin")
                .asFlow()
                .collect { append(it).append(", ") }
        }.removeSuffix(", ")
        assertEquals(inscription, "mene, mene, tekel, upharsin")
    }

    @Test
    fun when_collected_then_yields_a_value_in_many_ways() = runBlocking {
        val data = flow { "PEACE".asSequence().forEach { emit(it.toString()) } }

        val charList = data.toList()
        assertEquals(5, charList.size)

        val symbols = data.toSet()
        assertEquals(4, symbols.size)

        val word = data.reduce { acc, c -> acc + c }
        assertEquals("PEACE", word)

        val firstLetter = data.first()
        assertEquals("P", firstLetter)
    }

    @Test
    fun when_filtered_and_mapped_then_altered_values_collected() = runBlocking {
        val secretCodes = setOf(80, 69, 65, 67)
        val symbols = (60..100).asFlow()
            .filter { it in secretCodes }
            .map { Char(it) }
            .toList()
        assertEquals(listOf('A', 'C', 'E', 'P'), symbols)
    }

    @Test
    fun when_transformed_then_new_flow_is_formed() = runBlocking {
        val monthsOfSorrow = Month.values().asFlow()
            .transform {
                if (it <= Month.MAY)
                    emit(it)
            }
            .toList()
        assertEquals(5, monthsOfSorrow.size)
    }

    @Test
    fun when_limited_then_honours_the_limit() = runBlocking {
        val monthsOfSorrow = Month.values().asFlow().take(5).toList()
        assertEquals(5, monthsOfSorrow.size)
    }

    @Test
    fun when_collector_is_slow_and_conflate_is_used_then_values_are_skipped() = runBlocking {
        val buffer = mutableListOf<Int>()
        (1..10).asFlow()
            .transform {
                delay(10)
                emit(it)
            }
            .conflate()
            .collect {
                delay(50)
                buffer.add(it)
            }
        assertTrue { buffer.size < 10 }
    }

    @Test
    fun when_latest_is_collected_then_other_values_are_ignored() = runBlocking {
        var latest = -1
        (1..10).asFlow()
            .collectLatest { latest = it }
        assertEquals(10, latest)
    }

    @Test
    fun when_two_flows_are_zipped_then_input_from_two_data_sources_is_available() = runBlocking {
        val codes = listOf(80, 69, 65, 67).asFlow()
        val symbols = listOf('P', 'A', 'C', 'E').asFlow()
        val list = buildList {
            codes.zip(symbols) { code, symbol -> add("$code -> $symbol") }.collect()
        }
        assertEquals(listOf("80 -> P", "69 -> A", "65 -> C", "67 -> E"), list)
    }

    @Test
    fun when_two_flows_are_combined_then_they_both_are_updated() = runBlocking {
        val arrowsI = listOf("v", "<", "^", ">").asFlow().map { delay(30); it }
        val arrowsII = listOf("v", ">", "^", "<").asFlow().map { delay(20); it }
        arrowsI.combine(arrowsII) { one, two -> "$one $two" }
            .collect { println(it) }
    }

    @Test
    fun when_emitter_is_faster_then_the_collector_exerts_back_pressure() = runBlocking {
        val fastFlow = flow {
            (1..10).forEach {
                println("Before wating: $it")
                delay(10)
                println("About to emit: $it")
                emit(Char(60 + it))
                println("After emitting: $it")
            }
        }

        fastFlow.collect {
            Thread.sleep(100) // Imitate hard CPU-bound operation
            println("Collected $it")
        }
    }

    @Test
    fun when_emitter_has_its_own_context_then_collector_has_a_different_one() = runBlocking {
        flow {
            (1..10).forEach { emit(it) }
            assertTrue { "DefaultDispatcher-worker" in Thread.currentThread().name }
        }.flowOn(Dispatchers.IO)
            .collect {
                println(it)
                assertTrue { "main" in Thread.currentThread().name }
            }
    }

    @Test
    fun when_transformer_throws_then_catch_can_catch() = runBlocking {
        val result = (1..10).asFlow()
            .transform {
                if (it > 3) throw IllegalArgumentException("Too much")
                emit(it)
            }
            .catch {
                when (it) {
                    is IllegalArgumentException -> emit(3)
                    else -> throw it // rethrow unknown exceptions
                }
            }
            .toList()
        assertEquals(listOf(1, 2, 3, 3), result)
    }
}
