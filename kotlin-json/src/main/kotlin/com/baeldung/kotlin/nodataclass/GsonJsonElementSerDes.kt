package com.baeldung.kotlin.nodataclass

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser

object GsonJsonElementSerDes : JsonSerDes<JsonObject> {

    private val gson = Gson()

    override fun toJson(input: JsonObject): String = gson.toJson(input)

    override fun fromJson(input: String): JsonObject = JsonParser.parseString(input).asJsonObject

}