package com.baeldung.csv.kotlincsv

import com.github.doyaaaaaken.kotlincsv.util.CSVFieldNumDifferentException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

internal class KotlinCsvAssistedCSVOperationsUnitTest {

    @Test
    fun when_relaxed_file_is_read_then_everything_fails(): Unit = assertThrows<CSVFieldNumDifferentException>{
        readRelaxedCsv(javaClass.getResourceAsStream("../apache/taxables.csv")!!)
    }.let { }

    @Test
    fun when_file_is_read_then_domain_objects_are_populated() {
        val goods = readStrictCsv(javaClass.getResourceAsStream("taxables.csv")!!)
        assertEquals(10, goods.size)
        val sunscreen = goods.find { it.index == 6 }
        assertNotNull(sunscreen)
        assertEquals(BigDecimal("6.68"), sunscreen?.cost)
    }
}