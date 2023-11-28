package com.baeldung.kotlin.tomap

import com.baeldung.kotlin.tomap.ReflectionMapHelper.toMapWithOnlyPrimaryConstructorProperties
import com.baeldung.kotlin.tomap.ToMapTestFixture.PROJECT
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

class ReflectionMapHelperUnitTest {

    @Test
    fun whenConvertToMap_thenGetMapWithAllProperties() {
        assertEquals(
            mapOf(
                "name" to "test1",
                "type" to ProjectType.APPLICATION,
                "createdDate" to Date(1000),
                "repository" to mapOf(
                    "url" to "http://test.baeldung.com/test1"
                ),
                "deleted" to false,
                "owner" to null,
                "description" to "a new project"
            ),
            ReflectionMapHelper.toMap(PROJECT)
        )
    }

    @Test
    fun whenConvertToMapWithPrimaryConstructorProperties_thenGetMapWithExpectedProperties() {
        assertEquals(
            mapOf(
                "name" to "test1",
                "type" to ProjectType.APPLICATION,
                "createdDate" to Date(1000),
                "repository" to mapOf(
                    "url" to "http://test.baeldung.com/test1"
                ),
                "deleted" to false,
                "owner" to null
            ),
            toMapWithOnlyPrimaryConstructorProperties(PROJECT)
        )
    }
}