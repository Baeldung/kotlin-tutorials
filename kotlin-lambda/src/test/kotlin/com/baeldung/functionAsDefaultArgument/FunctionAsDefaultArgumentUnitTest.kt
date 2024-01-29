package com.baeldung.functionAsDefaultArgument

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun greeting(name: String = "Baeldung"): String {
    return "Hi $name"
}

fun greeting1(name: String = "Baeldung", buildMessage: (String) -> String): String {
    return "Hi $name, ${buildMessage(name)}"
}

fun greeting2(name: String = "Baeldung", buildMessage: (String) -> String = { input: String ->
    "your name is ${if (input.length > 3) "long" else "short"}."
}): String {
    return "Hi $name, ${buildMessage(name)}"
}

fun greeting3(name: String = "Baeldung", buildMessage: (String) -> String = { _ -> "how do you do?" }): String {
    return "Hi $name, ${buildMessage(name)}"
}

class FunctionAsDefaultArgumentUnitTest {

    val msgByCharCount: (String) -> String = { input: String ->
        "your name has an ${if (input.length % 2 == 1) "odd" else "even"} number of letters."
    }

    @Test
    fun `when calling greeting with or without passing parameter, then get expected result`() {
        assertEquals("Hi Baeldung", greeting())
        assertEquals("Hi Kai", greeting("Kai"))
    }

    @Test
    fun `when calling greeting1, then get expected result`() {
        val msgWithDefaultName = greeting1(buildMessage = msgByCharCount)
        assertEquals("Hi Baeldung, your name has an even number of letters.", msgWithDefaultName)

        val kaiMsg = greeting1(name = "Kai", buildMessage = msgByCharCount)
        assertEquals("Hi Kai, your name has an odd number of letters.", kaiMsg)
    }

    @Test
    fun `when calling greeting2, then get expected result`() {
        val result1 = greeting2()
        assertEquals("Hi Baeldung, your name is long.", result1)

        val result2 = greeting2("Kai")
        assertEquals("Hi Kai, your name is short.", result2)

        val result3 = greeting2(buildMessage = msgByCharCount)
        assertEquals("Hi Baeldung, your name has an even number of letters.", result3)

        val result4 = greeting2(name = "Kai", buildMessage = msgByCharCount)
        assertEquals("Hi Kai, your name has an odd number of letters.", result4)
    }

    @Test
    fun `when calling greeting3, then get expected result`() {
        val result1 = greeting3()
        assertEquals("Hi Baeldung, how do you do?", result1)

        val result2 = greeting3("Kai")
        assertEquals("Hi Kai, how do you do?", result2)

        val result3 = greeting3(buildMessage = msgByCharCount)
        assertEquals("Hi Baeldung, your name has an even number of letters.", result3)

        val result4 = greeting3(name = "Kai", buildMessage = msgByCharCount)
        assertEquals("Hi Kai, your name has an odd number of letters.", result4)
    }

}