package com.baeldung.dataclasstobytebuffer

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

@Serializable
data class UserSerialization(val id: Int, val name: String, val age: Int)

fun UserSerialization.userToJson(): ByteBuffer {
    val jsonString = Json.encodeToString(this)
    val byteArray = jsonString.toByteArray()
    return ByteBuffer.wrap(byteArray)
}

fun ByteBuffer.jsonToUser(): UserSerialization {
    val byteArray = ByteArray(this.remaining())
    this.get(byteArray)
    val jsonString = String(byteArray, StandardCharsets.UTF_8)
    return Json.decodeFromString(jsonString)
}