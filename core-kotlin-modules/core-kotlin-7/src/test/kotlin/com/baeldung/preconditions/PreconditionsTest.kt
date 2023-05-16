package com.baeldung.preconditions

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PreconditionsTest {

    @Test
    fun `printPositiveNumber throws exception for negative number`() {
        val num = -1
        val exception = assertThrows<IllegalArgumentException> {
            printPositiveNumber(num)
        }
        assert(exception.message == "Number must be positive")
    }

    @Test
    fun `printPositiveNumber does not throw exception for positive number`() {
        val num = 1
        printPositiveNumber(num)
    }

    @Test
    fun `printListSize throws exception for list with wrong size`() {
        val list = listOf(1, 2, 3)
        val size = 4
        val exception = assertThrows<IllegalStateException> {
            printListSize(list, size)
        }
        assert(exception.message == "List must contain $size elements")
    }

    @Test
    fun `printListSize does not throw exception for list with correct size`() {
        val list = listOf(1, 2, 3)
        val size = 3
        printListSize(list, size)
    }

    @Test
    fun `Person with non-null name and age should create a Person instance`() {
        val name = "John"
        val age = 30

        val person = Person(name, age)

        assertNotNull(person)
        assertEquals(name, person.name)
        assertEquals(age, person.age)
    }

    @Test
    fun `Person with null name should throw an IllegalArgumentException`() {
        val name: String? = null
        val age = 30

        assertThrows<IllegalArgumentException> {
            Person(name, age)
        }
    }

    @Test
    fun `Person with null age should throw an IllegalArgumentException`() {
        val name = "John"
        val age: Int? = null

        assertThrows<IllegalArgumentException> {
            Person(name, age)
        }
    }

    @Test
    fun `divide should throw an IllegalStateException when the second argument is zero`() {
        val a = 10
        val b = 0

        assertThrows<IllegalStateException> {
            divide(a, b)
        }
    }

    @Test
    fun `calculating average of empty list should throw IllegalStateException`() {

        val calculator = AverageCalculator()

        val exception = assertThrows<IllegalStateException> {
            calculator.average()
        }

        assert(exception.message == "Must have numbers to be able to perform an average.")
    }

    @Test
    fun `adding numbers and calculating average should return correct result`() {

        val calculator = AverageCalculator()

        calculator.add(3)
        calculator.add(7)
        calculator.add(10)
        assertEquals(6.67, calculator.average(), 0.01)
    }

    @Test
    fun `parsing json when a case is not supported error should be thrown`() {

        val exception = assertThrows<IllegalStateException> {
            val json = """
            "Hello, World!"
        """.trimIndent()
            val genericJson: JsonNode = jacksonObjectMapper().readTree(json)

            when (genericJson) {
                is ArrayNode -> { /* we have a json list */
                }

                is ObjectNode -> { /* we have an object */
                }

                else -> error("This function only handles list and object wrappers in Json")
            }
        }

        assert(exception.message == "This function only handles list and object wrappers in Json")
    }
}
