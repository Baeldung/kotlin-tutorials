package com.baeldung.yaml.kaml

import com.baeldung.yaml.model.Address
import com.baeldung.yaml.model.User
import com.baeldung.yaml.model.Users
import com.charleskorn.kaml.*
import kotlinx.serialization.decodeFromString


fun getUsersUsingUsersSerializer(fileContent: String): Users {
    val yaml = Yaml(configuration = YamlConfiguration(strictMode = false))
    val data = yaml.decodeFromString<Users>(fileContent)
    return data
}

fun getUsersUsingYamlMapSerializer(fileContent: String): Users {
    val yaml = Yaml(configuration = YamlConfiguration(strictMode = false))
    val result: MutableList<User> = mutableListOf()

    val data: YamlMap = yaml.parseToYamlNode(fileContent).yamlMap
    val usersYamlList: YamlList = data.get<YamlList>("users")!!.yamlList

    usersYamlList.items.forEach { userYamlListItem ->
        val name = (userYamlListItem as YamlMap).getScalar("name")!!.content
        val age = userYamlListItem.getScalar("age")!!.toInt()
        val addressYamlMap = userYamlListItem.get<YamlMap>("address")!!
        val city = addressYamlMap.getScalar("city")!!.content
        val country = addressYamlMap.getScalar("country")!!.content
        val user = User(name, age, Address(city, country))
        result.add(user)
    }

    return Users(result)
}