package com.baeldung.returnAtLabel

import com.baeldung.returnAtLabel.Answer.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory


val input = """
    T, T, T, T, T, T
    F, O, F, O, F, O
    T, F, O, F
    T, O, T, O, T, O
    T, X, T, X, T, X
    F, F, F, F, F, F
""".trimIndent()

enum class Answer {
    True, False, Empty
}

val expectedResult = listOf(
    listOf(True, True, True, True, True, True),
    listOf(False, Empty, False, Empty, False, Empty),
    listOf(True, Empty, True, Empty, True, Empty),
    listOf(True, True, True),
    listOf(False, False, False, False, False, False),
)


class ReturnAtLabelUnitTest {
    val log = LoggerFactory.getLogger(this::class.java)

    lateinit var resultList: MutableList<List<Answer>>


    fun printResult() {
        log.info(
            """
               |The Result After Processing:
               |----------------
               |${resultList.joinToString(separator = System.lineSeparator()) { "$it" }}
               |----------------
            """.trimMargin())
    }

    fun processInputV1(input: String) {
        resultList = mutableListOf()
        input.lines().forEach { line ->
            val fields = line.split(", ")
            if (fields.size != 6) return
            val answerList: MutableList<Answer> = mutableListOf()
            fields.forEach { field ->
                answerList += when (field) {
                    "T" -> True
                    "F" -> False
                    "O" -> Empty
                    else -> return
                }
            }
            resultList += answerList
        }
    }

    fun processInputV2(input: String) {
        resultList = mutableListOf()
        input.lines().forEach { line ->
            val fields = line.split(", ")
            if (fields.size != 6) return@forEach
            val answerList: MutableList<Answer> = mutableListOf()
            fields.forEach { field ->
                answerList += when (field) {
                    "T" -> True
                    "F" -> False
                    "O" -> Empty
                    else -> return
                }
            }
            resultList += answerList
        }
    }

    fun processInputV3(input: String) {
        resultList = mutableListOf()
        input.lines().forEach { line ->
            val fields = line.split(", ")
            if (fields.size != 6) return@forEach
            val answerList: MutableList<Answer> = mutableListOf()
            fields.forEach { field ->
                answerList += when (field) {
                    "T" -> True
                    "F" -> False
                    "O" -> Empty
                    else -> return@forEach
                }
            }
            resultList += answerList
        }
    }

    fun processInputV4(input: String) {
        resultList = mutableListOf()
        input.lines().forEach lineProcessing@{ line ->
            val fields = line.split(", ")
            if (fields.size != 6) return@lineProcessing
            val answerList: MutableList<Answer> = mutableListOf()
            fields.forEach answerProcessing@{ field ->
                answerList += when (field) {
                    "T" -> True
                    "F" -> False
                    "O" -> Empty
                    else -> return@answerProcessing
                }
            }
            resultList += answerList
        }
    }

    @Test
    fun `when using return directly in lambda, then exit the enclosing function`() {
        processInputV1(input)
        assertNotEquals(expectedResult, resultList)
        printResult()
    }

    @Test
    fun `when using return directly in the inner foreach lambda, then exit the enclosing function`() {
        processInputV2(input)
        assertNotEquals(expectedResult, resultList)
        printResult()
    }

    @Test
    fun `when using return with the default label in both foreach lambdas, then get the expected result`() {
        processInputV3(input)
        assertEquals(expectedResult, resultList)
    }

    @Test
    fun `when using return with custom labels in both foreach lambdas, then get the expected result`() {
        processInputV4(input)
        assertEquals(expectedResult, resultList)
    }

}