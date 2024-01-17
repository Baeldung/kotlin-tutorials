package changeMutableListValue

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ChangeMutableListValueUnitTest {

    @Test
    fun `changes mutable list value using set method`() {
        val mutableList = mutableListOf("kotlin", "java", "android")
        mutableList.set(1, "swift")
        assertEquals(mutableListOf("kotlin", "swift", "android"), mutableList)
    }

    @Test
    fun `changes mutable list value using indexed access operator`() {
        val mutableList = mutableListOf("kotlin", "java", "android")
        mutableList[1] = "swift"
        assertEquals(mutableListOf("kotlin", "swift", "android"), mutableList)
    }

    @Test
    fun `changes mutable list value using the map method`() {
        val mutableList = mutableListOf("kotlin", "java", "android")
        val updatedList = mutableList.map { element ->
            if (element == "java") {
                "swift"
            } else {
                element
            }
        }
        assertEquals(mutableListOf("kotlin", "swift", "android"), updatedList)
    }

    @Test
    fun `changes mutable list value using the replace method`() {
        val mutableList = mutableListOf("kotlin", "java", "android")
        mutableList.replaceAll { if (it == "java") "swift" else it }
        assertEquals(mutableListOf("kotlin", "swift", "android"), mutableList)
    }
}