package com.baeldung.kotlin.tomap

import com.baeldung.kotlin.tomap.KotlinSerializationMapHelper.JSON
import com.baeldung.kotlin.tomap.ToMapTestFixture.SERIALIZABLE_PROJECT
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KotlinSerializationMapHelperUnitTest {

    @Test
    fun whenConvertToMap_thenGetExpected() {
        val map = KotlinSerializationMapHelper.toMap(SERIALIZABLE_PROJECT)
        val expected = mapOf(
            "name" to "test1",
            "type" to ProjectType.APPLICATION.name,
            "createdDate" to MapHelper.DATE_FORMAT.format(SERIALIZABLE_PROJECT.createdDate),
            "repository" to mapOf(
                "url" to "http://test.baeldung.com/test1"
            ),
            "deleted" to false.toString(),
            "owner" to null,
            "description" to "a new project"
        )
        assertEquals(expected, map)
    }

    @Test
    fun whenConvertFromMap_thenGetExpectedObject() {
        val jsonObject = JSON.encodeToJsonElement(SERIALIZABLE_PROJECT).jsonObject
        val newProject = JSON.decodeFromJsonElement<SerializableProject>(jsonObject)
        assertEquals(SERIALIZABLE_PROJECT, newProject)
    }
}
