package com.baeldung.gson

import com.google.gson.Gson

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test

class GsonUnitTest {

    var gson = Gson()

    @Test
    fun givenObject_thenGetJSONString() {
        var jsonString = gson.toJson(TestModel(1, "Test"))
        assertEquals(jsonString, "{\"id\":1,\"description\":\"Test\"}")
    }

    @Test
    fun givenJSONString_thenGetObject() {
        var jsonString = "{\"id\":1,\"description\":\"Test\"}";
        var testModel = gson.fromJson(jsonString, TestModel::class.java)
        assertEquals(testModel.id, 1)
        assertEquals(testModel.description, "Test")
    }

    data class TestModel(
        val id: Int,
        val description: String
    )
}