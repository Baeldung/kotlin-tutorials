package com.baeldung.kotlin.nodataclass

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

object JacksonGenericMapSerDes : JsonSerDes<Map<String, Any?>> {

    private val mapper = jacksonObjectMapper()

    override fun toJson(input: Map<String, Any?>): String = mapper.writeValueAsString(input)

    override fun fromJson(input: String): Map<String, Any?> = mapper.readValue(input)

}