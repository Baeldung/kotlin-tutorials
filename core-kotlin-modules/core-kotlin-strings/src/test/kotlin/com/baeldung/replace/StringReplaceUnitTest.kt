package com.baeldung.replace

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StringReplaceUnitTest {

    @Test
    fun `should be able to replace some characters`() {
        val txt = "I am a robot"
        val replaced = txt.replace('a', 'A')
        assertEquals("I Am A robot", replaced)
    }

    @Test
    fun `should be to ignore cases while replacing`() {
        val txt = "I am a robot"
        val replaced = txt.replace('i', 'i', ignoreCase = true)
        assertEquals("i am a robot", replaced)
    }

    @Test
    fun `should be able to replace substrings inside another string`() {
        val txt = "I am a robot"
        val replaced = txt.replace("robot", "human")
        assertEquals("I am a human", replaced)
    }

    @Test
    fun `should be able to replace substrings inside another string -- case insensitive`() {
        val txt = "I am a robot"
        val replaced = txt.replace("i am a", "we are", true)
        assertEquals("we are robot", replaced)
    }

    @Test
    fun `should be able to replace with regular expressions`() {
        val txt = "<div>This is a div tag</div>"
        val regex = "</?.*?>".toRegex() // matches with every <tag> or </tag>
        val replaced = txt.replace(regex, "")
        assertEquals("This is a div tag", replaced)
    }

    @Test
    fun `should pass each pattern match to lambda`() {
        val txt = "<div>This is a div tag</div>"
        val regex = "</?.*?>".toRegex()
        val replaced = txt.replace(regex) {
            it.value.toUpperCase()
        }
        assertEquals("<DIV>This is a div tag</DIV>", replaced)
    }

    @Test
    fun `should be able to replace the first occurrence of a char`() {
        val txt = "I am a robot"
        val replaced = txt.replaceFirst('a', 'A')
        assertEquals("I Am a robot", replaced)
    }

    @Test
    fun `should be able to replace the first occurrence of a string`() {
        val txt = "42 42"
        val replaced = txt.replaceFirst("42", "The answer is")
        assertEquals("The answer is 42", replaced)
    }

    @Test
    fun `should be able to replace the first occurrence of a regex`() {
        val txt = "<div>this is a div</div>"
        val replaced = txt.replaceFirst("</?.*?>".toRegex(), "")
        assertEquals("this is a div</div>", replaced)
    }

    @Test
    fun `should be able to replace a range of characters with something else`() {
        val txt = "42 is the answer"
        val replaced = txt.replaceRange(0, 3, "")
        assertEquals("is the answer", replaced)
    }

    @Test
    fun `should be able to replace a range of characters with range syntax`() {
        val txt = "42 is the answer"
        assertEquals("is the answer", txt.replaceRange(0..2, ""))
        assertEquals("is the answer", txt.replaceRange(0 until 3, ""))
    }

    @Test
    fun `should be to replace all characters before a matched delimiter`() {
        val txt = "42 is the answer"
        assertEquals("is the answer", txt.replaceBefore('i', ""))
        assertEquals("is the answer", txt.replaceBefore("is", ""))
        assertEquals("42 is the answer", txt.replaceBefore("not a match", ""))
        assertEquals("default", txt.replaceBefore("not a match", "", "default"))
        assertEquals("swer", txt.replaceBeforeLast('s', ""))
    }

    @Test
    fun `should be to replace all characters after a matched delimiter`() {
        val txt = "42 is the answer"
        assertEquals("42 i", txt.replaceAfter('i', ""))
        assertEquals("42 is", txt.replaceAfter("is", ""))
        assertEquals("42 is the answer", txt.replaceAfter("not a match", ""))
        assertEquals("default", txt.replaceAfter("not a match", "", "default"))
        assertEquals("42 is the ans", txt.replaceAfterLast('s', ""))
    }

    @Test
    fun `should be able to replace indentations and margins`() {
        assertEquals("starts with indent", "    starts with indent".replaceIndent())
        assertEquals("==> starts with indent", "    starts with indent".replaceIndent("==> "))
    }

    @Test
    fun `should drop some characters from a string`() {
        val txt = "42 is the answer"
        assertEquals("is the answer", txt.drop(3))
        assertEquals("42 is the", txt.dropLast(7))
        assertEquals(" is the answer", txt.dropWhile { it != ' ' })
        assertEquals("42 is the ", txt.dropLastWhile { it != ' ' })
    }

    @Test
    fun `should keep some characters and throw the rest away`() {
        val txt = "42 is the answer"
        assertEquals("42", txt.take(2))
        assertEquals("answer", txt.takeLast(6))
        assertEquals("42", txt.takeWhile { it != ' ' })
        assertEquals("answer", txt.takeLastWhile { it != ' ' })
    }

    @Test
    fun `should be able to trim the beginning or end of a string`() {
        assertEquals("both ends", "  both ends ".trim())
        assertEquals("both ends", "###both ends!!".trim('#', '!'))
        assertEquals("both ends", "#?!both ends@".trim { !it.isLetter() && it != ' ' })

        assertEquals("just the beginning  ", "  just the beginning  ".trimStart())
        assertEquals("just the beginning##", "##just the beginning##".trimStart('#'))
        assertEquals("just the beginning  ", " #%just the beginning  ".trimStart { !it.isLetter() })

        assertEquals("  just the ending", "  just the ending  ".trimEnd())
        assertEquals("##just the beginning", "##just the beginning##".trimEnd('#'))
        assertEquals(" #%just the beginning", " #%just the beginning  ".trimEnd { !it.isLetter() })
    }

    @Test
    fun `should be able to remove some parts of the string`() {
        assertEquals("single line comment", "//single line comment".removePrefix("//"))
        assertEquals("end of multiline comment", "end of multiline comment*/".removeSuffix("*/"))
        assertEquals("some regex", "/some regex/".removeSurrounding("/"))
        assertEquals("/sample", "/sample".removeSurrounding("/"))
        assertEquals("multiline comment", "/*multiline comment*/".removeSurrounding("/*", "*/"))
        assertEquals("a robot", "I'm a robot".removeRange(0..3))
        assertEquals("a robot", "I'm a robot".removeRange(0 until 4))
        assertEquals("a robot", "I'm a robot".removeRange(0, 4))
    }
}
