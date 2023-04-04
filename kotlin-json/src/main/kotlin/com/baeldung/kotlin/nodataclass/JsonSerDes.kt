package com.baeldung.kotlin.nodataclass

interface JsonSerDes<C> : JsonSer<C>, JsonDes<C>

fun interface JsonSer<C> {
    fun toJson(input: C): String
}

fun interface JsonDes<C> {
    fun fromJson(input: String): C
}