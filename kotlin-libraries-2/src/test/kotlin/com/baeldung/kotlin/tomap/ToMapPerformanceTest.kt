package com.baeldung.kotlin.tomap

import com.baeldung.kotlin.tomap.ReflectionMapHelper.toMap
import com.baeldung.kotlin.tomap.ToMapTestFixture.PROJECT
import com.baeldung.kotlin.tomap.ToMapTestFixture.SERIALIZABLE_PROJECT
import com.google.gson.GsonBuilder
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.system.measureTimeMillis

class ToMapPerformanceTest {

    @Test
    fun testPerformance() {
        val projects = (0..100000).map {
            PROJECT.copy(
                name = "project$it",
                createdDate = Date(PROJECT.createdDate.time + it),
                repository = PROJECT.repository.copy(url = "${PROJECT.repository.url}$it"),
                owner = "owner$it"
            )
        }

        val reflection = measureTimeMillis {
            projects.forEach {
                toMap(it)
            }
        }
        println("reflection = $reflection")

        val jackson = measureTimeMillis {
            projects.forEach {
                JacksonMapHelper.JACKSON_MAPPER_WITH_DATE_FORMAT.convertValue(it, Map::class.java)
            }
        }
        println("jackson = $jackson")

        val gson = measureTimeMillis {
            val gson = GsonBuilder().serializeNulls().create()
            projects.forEach {
                gson.fromJson(gson.toJson(it), Map::class.java)
            }
        }
        println("gson = $gson")


        val serializableProjects = (0..100000).map {
            SERIALIZABLE_PROJECT.copy(
                name = "project$it",
                createdDate = Date(SERIALIZABLE_PROJECT.createdDate.time + it),
                repository = SERIALIZABLE_PROJECT.repository.copy(url = "${PROJECT.repository.url}$it"),
                owner = "owner$it"
            )
        }

        val serialization = measureTimeMillis {
            serializableProjects.forEach {
                KotlinSerializationMapHelper.toMap(it)
            }
        }
        println("serialization = $serialization")
    }

}


