package removeNullAndEmptyValues

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RemoveNullAndEmptyValuesUnitTest {

    @Test
    fun `remove null and empty values from list via list iteration`() {
        val listWithNullsAndEmpty: MutableList<String?> = mutableListOf("A", null, "", "C", null, "E")
        val listWithoutNullsAndEmpty = removeValuesViaIteration(listWithNullsAndEmpty)
        assertEquals(listOf("A", "C", "E"), listWithoutNullsAndEmpty)
    }

    @Test
    fun `remove null and empty values from list using filterNotNull and filterNot methods`() {
        val listWithNullsAndEmpty: List<String?> = listOf("A", null, "", "C", null, "E")
        val listWithoutNulls: List<String> = listWithNullsAndEmpty.filterNotNull()

        val listWithoutNullsAndEmpty: List<String> = listWithoutNulls.filterNot { it.isEmpty() }

        assertEquals(listOf("A", "", "C", "E"), listWithoutNulls)
        assertEquals(listOf("A", "C", "E"), listWithoutNullsAndEmpty)
    }

    @Test
    fun `remove null and empty values from list using removeIf method`() {
        val listWithNullsAndEmpty: MutableList<String?> = mutableListOf("A", null, "", "C", null, "E")
        listWithNullsAndEmpty.removeIf { it == null || it.isEmpty() }

        assertEquals(listOf("A", "C", "E"), listWithNullsAndEmpty)
    }

    fun removeValuesViaIteration(listWithNullsAndEmpty: MutableList<String?>): List<String> {
        val iterator = listWithNullsAndEmpty.iterator()
        while (iterator.hasNext()) {
            val element = iterator.next()
            if (element.isNullOrEmpty()) {
                iterator.remove()
            }
        }
        @Suppress("UNCHECKED_CAST")
        return listWithNullsAndEmpty as List<String>
    }
}