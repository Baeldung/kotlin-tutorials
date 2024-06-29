package com.baeldung.serialization.gson

import com.baeldung.serialization.Status
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class StatusDeserializer : JsonDeserializer<Status> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Status {
        return try {
            Status.valueOf(json.asString.uppercase())
        } catch (e: IllegalArgumentException) {
            Status.UNKNOWN
        }
    }
}