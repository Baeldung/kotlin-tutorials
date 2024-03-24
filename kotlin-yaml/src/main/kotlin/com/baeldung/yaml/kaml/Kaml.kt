package com.baeldung.yaml.kaml

import com.charleskorn.kaml.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import java.io.File

@Serializable
data class MetadataDeployment(val name: String)
@Serializable
data class Metadata(val labels: Map<String, String>)
@Serializable
data class Spec(val replicas: Int, val selector: MatchLabels, val template: Template)
@Serializable
data class MatchLabels(val matchLabels: Map<String, String>)
@Serializable
data class Template(val metadata: Metadata, val spec: TemplateSpec)
@Serializable
data class TemplateSpec(val containers: List<Container>)
@Serializable
data class Container(val name: String, val image: String, val ports: List<Port>)
@Serializable
data class Port(val containerPort: Int)

@Serializable
data class Deployment(val apiVersion: String, val kind: String, val metadata: MetadataDeployment, val spec: Spec)


@Serializable
data class Team(
    val leader: String,
    val members: List<String>
)


fun getDeployment(): Deployment {
    val yaml = Yaml(configuration = YamlConfiguration(strictMode = false))
//    val yaml = Yaml { configuration = }
    val file = File("/Users/tavasthi/IdeaProjects/kotlin-tutorials/kotlin-yaml/src/test/resources/deployment.yaml")

    val fileContent = Thread.currentThread().contextClassLoader.getResourceAsStream("deployment.yaml").bufferedReader().use { it.readText() }
    //println(fileContent!!::class)
    val data = fileContent.let { yaml.decodeFromString<Deployment>(it) }
    return data
}

fun main() {
//     val yaml = Yaml.default
    val yaml = Yaml(configuration = YamlConfiguration(strictMode = false))
//    val yaml = Yaml { configuration = }
    val file = File("/Users/tavasthi/IdeaProjects/kotlin-tutorials/kotlin-yaml/src/test/resources/deployment.yaml")

    val fileContent = Thread.currentThread().contextClassLoader.getResourceAsStream("deployment.yaml").bufferedReader().use { it.readText() }
    //println(fileContent!!::class)

    val input = """
        leader: Amy
        members:
          - Bob
          - Cindy
          - Dan
    """.trimIndent()

    //val data = yaml.decodeFromString<Deployment>(file.readText())
    val data = fileContent.let { yaml.decodeFromString<Deployment>(it) }

    println(data.spec.template.metadata.labels.keys)


    val yamlNode = Yaml.default.parseToYamlNode(input)
    println(
        yamlNode
            .yamlMap.get<YamlList>("members")!![1]
            .yamlScalar
            .content
    )


//
//    println("Replicas: ${data.replicas}")
//    println("Selector: ${data.selector}")
//    println("Template Metadata Name: ${data.template.metadata.name}")
//    println("Container Name: ${data.template.spec.containers[0].name}")
//    println("Container Image: ${data.template.spec.containers[0].image}")
//    println("Container Port: ${data.template.spec.containers[0].ports[0].containerPort}")
}