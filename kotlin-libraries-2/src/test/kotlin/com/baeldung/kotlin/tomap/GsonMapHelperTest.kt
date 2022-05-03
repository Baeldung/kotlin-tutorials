package com.baeldung.kotlin.tomap

import com.baeldung.kotlin.tomap.GsonMapHelper.GSON_MAPPER
import com.baeldung.kotlin.tomap.GsonMapHelper.KOTLIN_GSON_MAPPER
import com.baeldung.kotlin.tomap.ToMapTestFixture.PROJECT
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GsonMapHelperTest {

    @Test
    fun whenConvertToMapWithDateFormat_thenGetExpectedDate() {
        val expected = mapOf(
            "name" to "test1",
            "type" to ProjectType.APPLICATION.name,
            "createdDate" to MapHelper.DATE_FORMAT.format(PROJECT.createdDate),
            "repository" to mapOf(
                "url" to "http://test.baeldung.com/test1"
            ),
            "deleted" to false,
            "owner" to null,
            "description" to "a new project"
        )
        assertEquals(
            expected,
            GSON_MAPPER.fromJson(GSON_MAPPER.toJson(PROJECT), Map::class.java)
        )
    }

    @Test
    fun whenConvertFromMap_thenGetExpectedObject() {
        val map = mapOf(
            "name" to "test1",
            "type" to ProjectType.APPLICATION.name,
            "createdDate" to MapHelper.DATE_FORMAT.format(PROJECT.createdDate),
            "repository" to mapOf(
                "url" to "http://test.baeldung.com/test1"
            ),
            "deleted" to false,
            "owner" to null,
            "description" to "a new project"
        )
        assertEquals(
            PROJECT,
            GSON_MAPPER.fromJson(GSON_MAPPER.toJson(map), Project::class.java)
        )
    }

    @Test
    fun whenConvertFromMapMissingProperty_thenGetIncorrectObjectWithNullValueForNonNullableProperty() {
        val map = mapOf(
            "name" to "test1",
            "type" to ProjectType.APPLICATION.name,
            "repository" to mapOf(
                "url" to "http://test.baeldung.com/test1"
            ),
            "deleted" to false,
            "owner" to null,
            "description" to "a new project"
        )
        val newProject = GSON_MAPPER.fromJson(GSON_MAPPER.toJson(map), Project::class.java)
        assertNull(newProject.createdDate)
    }

    @Test
    fun whenConvertFromMapMissingProperty_thenThrowException() {
        val map = mapOf(
            "name" to "test1",
            "type" to ProjectType.APPLICATION.name,
            "repository" to mapOf(
                "url" to "http://test.baeldung.com/test1"
            ),
            "deleted" to false,
            "owner" to null,
            "description" to "a new project"
        )
        val exception = assertThrows<IllegalArgumentException> {
            KOTLIN_GSON_MAPPER.fromJson(KOTLIN_GSON_MAPPER.toJson(map), Project::class.java)
        }
        assertEquals(
            "Value of non-nullable property [${Project::createdDate.name}] cannot be null",
            exception.message
        )
    }
}