package com.baeldung.contextreceivers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

context(StringBuilder)
fun appendHello() {
    append("Hello, ")
}

context(StringBuilder)
fun appendWorld() {
    append("World!")
}

class HtmlBuilder {
    private val elements = mutableListOf<String>()
    fun addElement(tag: String, content: String) {
        elements.add("<$tag>$content</$tag>")
    }

    fun build(): String = elements.joinToString("\n")
}

context(HtmlBuilder) fun p(content: String) {
    addElement("p", content)
}

context(HtmlBuilder) fun h1(content: String) {
    addElement("h1", content)
}

class ContextReceiversUnitTest {

    fun html(content: HtmlBuilder.() -> Unit): String {
        val builder = HtmlBuilder()
        builder.content()
        return builder.build()
    }

    @Test
    fun `test StringBuilder context receiver`() {
        val builder = StringBuilder()
        with(builder) {
            appendHello()
            appendWorld()
        }
        assertEquals("Hello, World!", builder.toString())
    }

    @Test
    fun `test HTML DSL with context receivers`() {
        val htmlContent = html {
            h1("Welcome to My Website")
            p("This is a paragraph in my website.")
        }
        val expected = """
            <h1>Welcome to My Website</h1>
            <p>This is a paragraph in my website.</p>
            """.trimIndent()
        assertEquals(expected, htmlContent)
    }

}