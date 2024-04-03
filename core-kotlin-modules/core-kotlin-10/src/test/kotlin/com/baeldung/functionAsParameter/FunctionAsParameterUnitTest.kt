package com.baeldung.functionAsParameter

import com.baeldung.functionAsParameter.Sender.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun joinByOperation(theList: List<String>, operation: (List<String>) -> String): String {
    return operation(theList)
}


data class TheMessage(val sender: Sender, val segments: List<String>) {
    fun parse(operation: (List<String>) -> String): String {
        return "Message From $sender is: ${operation(segments)}"
    }
}

enum class Sender {
    User, RemoteAPI, PartnerApp, CsvSender
}


class MessageParser {
    fun joinWithoutPlaceholder(segments: List<String>): String {
        return segments.joinToString(separator = " ").replace(" [SPACE] ", " ")
    }

    companion object {
        fun simplyJoin(segments: List<String>): String {
            return segments.joinToString(separator = " ")
        }
    }
}


object ParserInObject {
    fun joinWithoutComma(segments: List<String>): String {
        return segments.joinToString(separator = " ") { it.replace(",", "") }
    }
}

fun decrypt(segments: List<String>): String {
    return segments.reversed().joinToString(separator = " ") { it.reversed() }
}

val messageParser = MessageParser()
fun parseMessage(message: TheMessage): String {
    return when (message.sender) {
        User -> message.parse(MessageParser::simplyJoin)
        PartnerApp -> message.parse(messageParser::joinWithoutPlaceholder)
        CsvSender -> message.parse(ParserInObject::joinWithoutComma)
        RemoteAPI -> message.parse(::decrypt)
    }
}

class FunctionAsParameterUnitTest {

    @Test
    fun `when passing lambda as parameters then get expected result`() {
        val input = listOf("a b c", "x y z", "kotlin")
        val result1 = joinByOperation(input) { theList ->
            theList.joinToString(separator = " ") { str -> str.reversed() }.replace(" ", ", ")
        }
        assertEquals("c, b, a, z, y, x, niltok", result1)

        val result2 = joinByOperation(input) { theList ->
            theList.reversed().joinToString(separator = " ") { str -> str }.uppercase()
        }
        assertEquals("KOTLIN X Y Z A B C", result2)

    }

    @Test
    fun `when passing existing fun as parameters then get expected result`() {


        val msgFromUser = TheMessage(User, listOf("a b c", "d e f", "x y z"))
        val resultUser = parseMessage(msgFromUser)
        assertEquals("Message From User is: a b c d e f x y z", resultUser)

        val msgFromPartner = TheMessage(PartnerApp, listOf("a [SPACE] b [SPACE] c", "d [SPACE] e [SPACE] f", "x [SPACE] y [SPACE] z"))
        val resultPartner = parseMessage(msgFromPartner)
        assertEquals("Message From PartnerApp is: a b c d e f x y z", resultPartner)

        val msgFromCsv = TheMessage(CsvSender, listOf("a, b, c", "d, e, f", "x, y, z"))
        val resultCsv = parseMessage(msgFromCsv)
        assertEquals("Message From CsvSender is: a b c d e f x y z", resultCsv)

        val msgFromAPI = TheMessage(RemoteAPI, listOf("z y x", "f e d", "c b a"))
        val resultApi = parseMessage(msgFromAPI)
        assertEquals("Message From RemoteAPI is: a b c d e f x y z", resultApi)
    }
}