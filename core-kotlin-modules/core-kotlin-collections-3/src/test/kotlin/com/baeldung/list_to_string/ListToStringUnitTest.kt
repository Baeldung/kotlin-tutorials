package kotlin.com.baeldung.list_to_string

import org.junit.Assert.assertEquals
import org.junit.Test

class ListToStringUnitTest {

    @Test
    fun `Given a list, when joinToString is executed, output is as comma-separated string`() {
        val numbers = listOf(11, 22, 3, 41, 52, 6)
        val string = numbers.joinToString()
        assertEquals("11, 22, 3, 41, 52, 6", string)
    }

    @Test
    fun `Given a list, when joinToString with prefix, postfix and separator setting is executed, output is string`() {
        val numbers = listOf(11, 22, 3, 41, 52, 6)
        val string = numbers.joinToString(prefix = "<", postfix = ">", separator = "")
        assertEquals("<1122341526>", string)
    }

    @Test
    fun `Given a list, when joinToString with no separator and limit setting is executed, output is string`() {
        val chars = charArrayOf('h', 'e', 'l', 'l', 'o', 'o', 'o', 'o')
        val string = chars.joinToString(separator = "", limit = 5, truncated = "!") { it.uppercaseChar().toString() }
        assertEquals("HELLO!", string)
    }

    @Test
    fun `Given a list, when joinTo, output is string`() {
        val sb = StringBuilder("An existing string and a list: ")
        val numbers = listOf(11, 22, 3, 41, 52, 6)
        val string = numbers.joinTo(sb).toString()
        assertEquals("An existing string and a list: 11, 22, 3, 41, 52, 6", string)
    }

    @Test
    fun `Given a list, when reduce is executed, output is string`() {
        val strings = listOf("a", "b", "c", "d")
        val string = strings.reduce { acc, string -> acc + string }
        assertEquals("abcd", string)
    }

    @Test
    fun `Given a list, when looping over string, output is string`() {
        val elements = listOf("a", "b", "c", "d", "e")
        var string = ""

        for(s in elements){
            string += s
        }

        assertEquals("abcde", string)
    }

    @Test
    fun `Given a list, when StringBuilder appends, output is string`() {
        val letters = listOf("a", "b", "c", "d", "e", "f")
        val builder = StringBuilder()

        for(s in letters){
            builder.append(s)
        }

        assertEquals("abcdef", builder.toString())
    }

    @Test
    fun `Given a list, when StringBuilder appends with suffix, output is string`() {
        val letters = listOf("a", "b", "c", "d", "e", "f")
        val builder = StringBuilder()

        for(s in letters){
            builder.append(s)
        }

        val withoutSuffix = builder.toString().removeSuffix("f")
        assertEquals("abcde", withoutSuffix)
    }
}
