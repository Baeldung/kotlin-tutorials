package com.baeldung.kotlin.nodataclass

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GsonGenericMapSerDes : JsonSerDes<Map<String, Any?>> {

    private val gson = Gson()

    private val mapAdapter = gson.getAdapter(object : TypeToken<Map<String, Any?>>() {})

    override fun toJson(input: Map<String, Any?>): String = mapAdapter.toJson(input)

    override fun fromJson(input: String): Map<String, Any?> = mapAdapter.fromJson(input) ?: mapOf()

}