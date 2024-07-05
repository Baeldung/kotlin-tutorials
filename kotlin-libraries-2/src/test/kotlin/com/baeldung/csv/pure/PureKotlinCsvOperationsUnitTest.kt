package com.baeldung.csv.pure

import com.baeldung.csv.model.Movie
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.time.Year
import kotlin.test.assertEquals

internal class PureKotlinCsvOperationsUnitTest {

    @Test
    fun when_file_is_read_then_domain_objects_are_populated() {
        val movies = readCsv(javaClass.getResourceAsStream("deniro.csv")!!)
        assertEquals(87, movies.size)
        assertEquals("Dear America: Letters Home From Vietnam", movies.maxByOrNull { it.score }?.title)
    }

    @Test
    fun write_csv() {
        val movies = listOf(
            Movie(1996.toYear(), 74, "Sleepers"),
            Movie(1996.toYear(), 38, "The Fan"),
            Movie(1996.toYear(), 80, "Marvin's Room"),
            Movie(1997.toYear(), 85, "Wag the Dog"),
            Movie(1997.toYear(), 87, "Jackie Brown"),
            Movie(1997.toYear(), 72, "Cop Land"),
            Movie(1998.toYear(), 68, "Ronin")
        )
        val csv = ByteArrayOutputStream().apply { writeCsv(movies) }
            .toByteArray().let { String(it) }
        assertEquals(
            """
                "Year", "Score", "Title"
                1996, 74, "Sleepers"
                1996, 38, "The Fan"
                1996, 80, "Marvin's Room"
                1997, 85, "Wag the Dog"
                1997, 87, "Jackie Brown"
                1997, 72, "Cop Land"
                1998, 68, "Ronin"
                
                """.trimIndent(), csv
        )
    }

    fun Int.toYear(): Year = Year.of(this)
}