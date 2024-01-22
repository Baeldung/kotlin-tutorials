package arrayListToJsonArray

import com.google.gson.Gson
import org.json.JSONArray
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ArrayListToJsonArrayUnitTest {

    @Test
    fun `convert ArrayList to JSONArray using for loop`() {
        val list = arrayListOf("one", "two", "three")
        val jsonArray = JSONArray()
        for (element in list) {
            jsonArray.put(element)
        }
        assertEquals(list.size, jsonArray.length())
        assertEquals("one", jsonArray[0])
        assertEquals("two", jsonArray[1])
        assertEquals("three", jsonArray[2])
    }

    @Test
    fun `convert ArrayList to JSONArray using JSONArray constructor`() {
        val list = arrayListOf("one", "two", "three")
        val jsonArray = JSONArray(list)

        assertEquals(list.size, jsonArray.length())
        assertEquals("one", jsonArray[0])
        assertEquals("two", jsonArray[1])
        assertEquals("three", jsonArray[2])
    }

    @Test
    fun `convert ArrayList to JSONArray using Gson library`() {
        val list = arrayListOf("one", "two", "three")
        val gson = Gson()
        val jsonArray = gson.toJsonTree(list).asJsonArray

        assertEquals(list.size, jsonArray.size())
        assertEquals("one", jsonArray[0].asString)
        assertEquals("two", jsonArray[1].asString)
        assertEquals("three", jsonArray[2].asString)
    }
}