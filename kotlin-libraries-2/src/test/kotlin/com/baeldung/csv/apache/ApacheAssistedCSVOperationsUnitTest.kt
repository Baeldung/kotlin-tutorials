package com.baeldung.csv.apache

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.io.StringWriter
import java.math.BigDecimal

internal class ApacheAssistedCSVOperationsUnitTest {
    @Test
    fun when_file_is_read_then_domain_objects_are_populated() {
        val goods = readCsv(javaClass.getResourceAsStream("taxables.csv")!!)
        assertEquals(10, goods.size)
        val sunscreen = goods.find { it.index == 6 }
        assertNotNull(sunscreen)
        assertEquals(BigDecimal("6.68"), sunscreen?.cost)
    }

    @Test
    fun when_domain_collection_is_written_then_valid_csv_is_produced() {
        val goods = readCsv(javaClass.getResourceAsStream("taxables.csv")!!)
        val renderedCsv = StringWriter().apply { writeCsv(goods) }.buffer.toString()
        val expected = """
                        Index,Item,Cost,Tax,Total
                        1,Fruit of the Loom Girl's Socks,7.97,0.60,8.57
                        2,Rawlings Little League Baseball,2.97,0.22,3.19
                        3,Secret Antiperspirant,1.29,0.10,1.39
                        4,Deadpool DVD,14.96,1.12,16.08
                        5,Maxwell House Coffee 28 oz,7.28,0.55,7.83
                        6,"Banana Boat Sunscreen, 8 oz",6.68,0.50,7.18
                        7,"Wrench Set, 18 pieces",10.00,0.75,10.75
                        8,"M and M, 42 oz",8.98,0.67,9.65
                        9,Bertoli Alfredo Sauce,2.12,0.16,2.28
                        10,"Large Paperclips, 10 boxes",6.19,0.46,6.65
                        
                        """.trimIndent().replace("\n", "\r\n")
        assertEquals(expected, renderedCsv)
    }
}