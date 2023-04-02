package com.baeldung.kotlin.nodataclass

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object JacksonJsonNodeSerDes : JsonSerDes<JsonNode> {

    private val mapper = jacksonObjectMapper()

    override fun toJson(input: JsonNode): String = mapper.writeValueAsString(input)

    override fun fromJson(input: String): JsonNode = mapper.readTree(input)

}