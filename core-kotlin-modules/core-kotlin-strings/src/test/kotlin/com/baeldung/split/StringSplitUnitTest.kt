package com.baeldung.split

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

class StringSplitUnitTest {

    @Test
    fun `split with one delimiter should work as expected`() {
        val info = "Name,Year,Location"
        assertThat(info.split(",")).containsExactly("Name", "Year", "Location")
    }

    @Test
    fun `split with multiple delimiters should work as expected`() {
        val info = "Name,Year,Location/Time"
        assertThat(info.split(",", "/")).containsExactly("Name", "Year", "Location", "Time")
    }

    @Test
    fun `should be able to limit the number of parts after split with delimiter`() {
        val info = "Name,Year,Location/Time/Date"
        assertThat(info.split(",", limit = 2)).containsExactly("Name", "Year,Location/Time/Date")
        assertThat(info.split(",", "/", limit = 4)).containsExactly("Name", "Year", "Location", "Time/Date")
    }

    @Test
    fun `ignore case should allow splitting in a case-insensitive manner`() {
        val info = "127.0.0.1aaFirefoxAA58"
        assertThat(info.split("aa", ignoreCase = true)).containsExactly("127.0.0.1", "Firefox", "58")
    }

    @Test
    fun `lines splits around new line chars`() {
        val info = "First line\nsecond line\rthird"
        assertThat(info.lines()).containsExactly("First line", "second line", "third")
    }

    @Test
    fun `split should allow using regex as the delimiter`() {
        val info = "28 + 32 * 2 / 64 = 29"
        val regex = "\\D+".toRegex()
        assertThat(info.split(regex)).containsExactly("28", "32", "2", "64", "29")

        val pattern = Pattern.compile("\\D+")
        assertThat(info.split(pattern)).containsExactly("28", "32", "2", "64", "29")
    }

    @Test
    fun `should be able to limit the regex splits`() {
        val info = "28 + 32 * 2 / 64 = 29"
        val regex = "\\D+".toRegex()
        assertThat(info.split(regex, 3)).containsExactly("28", "32", "2 / 64 = 29")
        val pattern = Pattern.compile("\\D+")
        assertThat(info.split(pattern, 3)).containsExactly("28", "32", "2 / 64 = 29")
    }

    @Test
    fun `splitToSequence works lazily`() {
        val info = "random_text,".repeat(1000)
        assertThat(info.splitToSequence(",").first()).isEqualTo("random_text")
    }
}
