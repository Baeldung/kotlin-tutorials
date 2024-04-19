package com.baeldung.hmtlBuilder

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HtmlBuilderUnitTest {

    @Test
    fun testBuildHtml() {
        val expectedHtml = """
            <html>
              <head>
                <title>My Kotlin HTML Page</title>
              </head>
              <body>
                <h1>Welcome to Kotlin HTML Builder</h1>
                <p>This is a demonstration of kotlinx.html.</p>
                <ul>
                  <li>Item 0</li>
                  <li>Item 1</li>
                  <li>Item 2</li>
                  <li>Item 3</li>
                  <li>Item 4</li>
                </ul>
              </body>
            </html>
        """.trimIndent()

        val actualHtml = buildHTML()

        assertEquals(expectedHtml, actualHtml)
    }
}
fun buildHTML(): String {
    return buildString {
        appendHTML().html {
            head {
                title { +"My Kotlin HTML Page" }
            }
            body {
                h1 { +"Welcome to Kotlin HTML Builder" }
                p { +"This is a demonstration of kotlinx.html." }
                ul {
                    repeat(5) {
                        li { +"Item $it" }
                    }
                }
            }
        }
    }.trim()
}