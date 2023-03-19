package com.baeldung.kotlin.nodataclass

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class JsonSerDesUnitTest {

    private val filePath = "src/test/resources/com/baeldung/kotlin/nodataclass/json_doc.json"

    private val jsonDocument = File(filePath).readText()

    @Test
    fun whenNoDataClassHasBeenSpecifiedWithKotlinx_thenCorrect() {
        checkGenericMapDeserialization(KotlinxGenericMapSerDes)
    }

    @Test
    fun whenReloadingFromDeserializedMapSerializationWithKotlinx_thenEquals() {
        checkGenericMapReloading(KotlinxGenericMapSerDes)
    }

    @Test
    fun whenNoDataClassHasBeenSpecifiedWithGson_thenCorrect() {
        checkGenericMapDeserialization(GsonGenericMapSerDes)
    }

    @Test
    fun whenReloadingFromDeserializedMapSerializationWithGson_thenEquals() {
        checkGenericMapReloading(GsonGenericMapSerDes)
    }

    @Test
    fun whenNoDataClassHasBeenSpecifiedWithMoshi_thenCorrect() {
        checkGenericMapDeserialization(MoshiGenericMapSerDes)
    }

    @Test
    fun whenReloadingFromDeserializedMapSerializationWithMoshi_thenEquals() {
        checkGenericMapReloading(MoshiGenericMapSerDes)
    }

    @Test
    fun whenNoDataClassHasBeenSpecifiedWithJackson_thenCorrect() {
        checkGenericMapDeserialization(JacksonGenericMapSerDes)
    }

    @Test
    fun whenReloadingFromDeserializedMapSerializationWithJackson_thenEquals() {
        checkGenericMapReloading(JacksonGenericMapSerDes)
    }
    
    private fun checkGenericMapReloading(serdes: JsonSerDes<Map<String, Any?>>) {
        val deserializedMap = serdes.fromJson(jsonDocument)
        val serializedJson = serdes.toJson(deserializedMap)
        val rebuildMap = serdes.fromJson(serializedJson)
        assertEquals(deserializedMap, rebuildMap)
    }

    private fun checkGenericMapDeserialization(serdes: JsonSerDes<Map<String, Any?>>) {
        checkDeserializedGenericMap(serdes.fromJson(jsonDocument))
    }

    private fun checkDeserializedGenericMap(map: Map<String, Any?>) {
        assertEquals("string", map["the_string"])
        assertEquals(3.14, map["the_number"])
        assertEquals(true, map["the_boolean"])

        assertEquals(listOf("string1", "string2"), (map["the_string_array"] as Iterable<*>).toList())
        assertEquals(listOf(1.0, 2.0), (map["the_number_array"] as Iterable<*>).toList())
        assertEquals(listOf(true, false), (map["the_boolean_array"] as Iterable<*>).toList())

        (map["the_null_array"] as Iterable<*>).forEach {
            assertNull(it)
        }

        assertTrue(map["the_object"] is Map<*, *>)
        assertEquals("Hello from nested object", (map["the_object"] as Map<*, *>)["message"])

        assertTrue(map["the_mixed_array"] is Iterable<*>)

        (map["the_mixed_array"] as Iterable<*>).forEachIndexed { idx, value ->
            when(idx) {
                0 -> assertEquals(1.0, value)
                1 -> assertEquals("string", value)
                2 -> assertNull(value)
                3 -> assertEquals(mapOf("message" to  "Hello from array object element"), value)
            }
        }

    }
}