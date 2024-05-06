package com.baeldung.eachCountMethods

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EachCountMethodsUnitTest {
    @Test
    fun `given a list of flights eachCount method should create a count per airline`() {
        val flights = listOf("EK060", "EK531", "LH7", "LH1030", "DL47", "AI120")
        val flightCount = flights.groupingBy { it.take(2) }.eachCount()
        val expectedMap = mapOf("EK" to 2, "LH" to 2, "DL" to 1, "AI" to 1)
        assertEquals(expectedMap, flightCount)
    }

    @Test
    fun `given a list of flights, use groupBy and count to implement eachCount logic`() {
        val flights = listOf("EK060", "EK531", "LH7", "LH1030", "DL47", "AI120")
        val flightCount = flights.groupBy { it.take(2) }.mapValues { it.value.count() }
        val expectedMap = mapOf("EK" to 2, "LH" to 2, "DL" to 1, "AI" to 1)
        assertEquals(expectedMap, flightCount)
    }

    @Test
    fun `given a list of flights eachCountTo method should create a count per airline`() {
        val flights = listOf("EK060", "EK531", "LH7", "LH1030", "DL47", "AI120")
        val flightCount = flights.groupingBy { it.take(2) }.eachCount().toMutableMap()
        val expectedMap = mutableMapOf("EK" to 2, "LH" to 2, "DL" to 1, "AI" to 1)
        assertEquals(expectedMap, flightCount)
        val moreFlights = listOf("EK061", "AI435")
        moreFlights.groupingBy { it.take(2) }.eachCountTo(flightCount)
        val expectedMapAfterMoreFlights = mutableMapOf("EK" to 3, "LH" to 2, "DL" to 1, "AI" to 2)
        assertEquals(expectedMapAfterMoreFlights, flightCount)
    }
}