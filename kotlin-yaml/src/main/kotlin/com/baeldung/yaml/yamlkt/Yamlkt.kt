package com.baeldung.yaml.yamlkt

import com.baeldung.yaml.model.Users
import net.mamoe.yamlkt.Yaml
import net.mamoe.yamlkt.YamlList
import net.mamoe.yamlkt.YamlMap
import net.mamoe.yamlkt.toYamlElement

fun getDeployment(fileContent: String): Users {
    //val fileContent = Thread.currentThread().contextClassLoader.getResourceAsStream("deployment.yaml").bufferedReader().use { it.readText() }.trimIndent()
    // val deployment = Yaml.decodeFromString<Deployment>(fileContent)
    // return deployment
    // val data = fileContent.let { Yaml.decodeFromString<Deployment>(it) }
    println(fileContent)
    //val yaml = Yaml()
    //val data = yaml.decodeFromString(Deployment.serializer(), fileContent)
    val data = Yaml.Default.decodeFromString<Users>(Users.serializer(), fileContent)
    return data
}


fun getDeployment2(fileContent: String): Users {

    // val deployment = Yaml.decodeFromString<Deployment>(fileContent)
    // return deployment
    // val data = fileContent.let { Yaml.decodeFromString<Deployment>(it) }
    println(fileContent)
    //val yaml = Yaml()
    //val data = yaml.decodeFromString(Deployment.serializer(), fileContent)
    val data = Yaml.Default.decodeFromString<Users>(Users.serializer(), fileContent.orEmpty())

    val c1 = Yaml.Default.decodeFromString<YamlMap>(YamlMap.serializer(), fileContent.orEmpty())
    val users = c1["users"] as YamlList

    users.forEach { user ->
        val name = (user as YamlMap)["name"]
        val age = user["age"]
        val address = user["address"]

        println("Name: $name, Age: $age, Address: $address")
    }

    return data
}