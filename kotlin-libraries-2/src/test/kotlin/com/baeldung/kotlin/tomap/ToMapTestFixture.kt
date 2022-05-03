package com.baeldung.kotlin.tomap

import kotlinx.serialization.Serializable
import java.util.*

object ToMapTestFixture {
    val PROJECT = Project(
        name = "test1",
        type = ProjectType.APPLICATION,
        createdDate = Date(1000),
        repository = ProjectRepository(url = "http://test.baeldung.com/test1"),
        owner = null
    ).apply {
        description = "a new project"
    }

    val SERIALIZABLE_PROJECT = SerializableProject(
        name = "test1",
        type = ProjectType.APPLICATION,
        createdDate = Date(1000),
        repository = SerializableProjectRepository(url = "http://test.baeldung.com/test1"),
        owner = null
    ).apply {
        description = "a new project"
    }

}

enum class ProjectType {
    APPLICATION, CONSOLE, WEB
}

data class ProjectRepository(val url: String)

data class Project(
    val name: String,
    val type: ProjectType,
    val createdDate: Date,
    val repository: ProjectRepository,
    val deleted: Boolean = false,
    val owner: String?
) {
    var description: String? = null
}


@Serializable
data class SerializableProjectRepository(val url: String)

@Serializable
data class SerializableProject(
    val name: String,
    val type: ProjectType,
    @Serializable(KotlinSerializationMapHelper.DateSerializer::class) val createdDate: Date,
    val repository: SerializableProjectRepository,
    val deleted: Boolean = false,
    val owner: String?
) {
    var description: String? = null
}