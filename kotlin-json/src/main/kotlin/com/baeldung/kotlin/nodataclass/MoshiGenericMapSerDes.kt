package com.baeldung.kotlin.nodataclass

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter

/**
 * Note: Moshi does not have a JsonElement generic class
 */
object MoshiGenericMapSerDes : JsonSerDes<Map<String, Any?>> {

    private val moshi = Moshi.Builder().build()

    @OptIn(ExperimentalStdlibApi::class)
    private val mapAdapter = moshi.adapter<Map<String, Any?>>().serializeNulls()

    override fun toJson(input: Map<String, Any?>): String = mapAdapter.toJson(input)

    override fun fromJson(input: String): Map<String, Any?> = mapAdapter.fromJson(input) ?: mapOf()

}