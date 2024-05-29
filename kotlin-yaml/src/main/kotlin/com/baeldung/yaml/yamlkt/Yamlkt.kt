package com.baeldung.yaml.yamlkt

import com.baeldung.yaml.model.Users
import com.baeldung.yaml.model.User
import com.baeldung.yaml.model.Address
import net.mamoe.yamlkt.*

fun getUsersUsingUsersSerializer(fileContent: String): Users {
    val users: Users = Yaml.Default.decodeFromString(Users.serializer(), fileContent)
    return users
}


fun getUsersUsingYamlMapSerializer(fileContent: String): Users {
    val result: MutableList<User> = mutableListOf()

    val data: YamlMap = Yaml.Default.decodeFromString(YamlMap.serializer(), fileContent)
    val usersYamlList: YamlList = data.get("users") as YamlList

    usersYamlList.forEach { userYamlListItem ->
        val name = (userYamlListItem as YamlMap).getString("name")
        val age = userYamlListItem.getInt("age")
        val addressYamlMap = (userYamlListItem.get("address") as YamlMap)
        val city = addressYamlMap.getString("city")
        val country = addressYamlMap.getString("country")
        val user = User(name, age, Address(city, country))
        result.add(user)
    }

    return Users(result)
}
