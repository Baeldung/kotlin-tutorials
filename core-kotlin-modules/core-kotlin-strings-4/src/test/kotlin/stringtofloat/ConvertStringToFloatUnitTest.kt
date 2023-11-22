package stringtofloat

import org.junit.jupiter.api.Test
import java.text.NumberFormat
import java.util.*
import kotlin.test.assertEquals

class ConvertStringToFloatUnitTest {

    @Test
    fun `convert string to float using the toFloat method`() {
        val myString = "8.73"
        val myString2 = "Invalid"

        assertEquals(8.73F, myString.toFloat())
        assertEquals(null, myString2.toFloatOrNull())
    }

    @Test
    fun `convert string to float using the NumberFormat class`() {
        assertEquals(8.73F, stringToFloat("8.73"))
    }

    @Test
    fun `convert string to float using Regex`() {
        assertEquals(8.73F, stringToFloatUsingRegex("8.73"))
        assertEquals(8.73F, stringToFloatUsingRegex("this is a float number 8.73"))
    }

    fun stringToFloat(input: String): Float {
        val nf = NumberFormat.getInstance(Locale.US)
        return nf.parse(input).toFloat()
    }

    fun stringToFloatUsingRegex(input: String): Float {
        val regex = Regex("[+-]?([0-9]*[.])?[0-9]+")
        val match = regex.find(input)
        return match?.value?.toFloatOrNull() ?: 0f
    }

}