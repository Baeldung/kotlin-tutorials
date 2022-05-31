package com.baeldung.kotlin.tomap

import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import kotlin.jvm.internal.Reflection
import kotlin.reflect.full.memberProperties

class KotlinTypeAdaptor<T>(private val delegate: TypeAdapter<T>, private val type: TypeToken<T>) : TypeAdapter<T>() {
    override fun write(out: JsonWriter, value: T?) = delegate.write(out, value)

    override fun read(input: JsonReader): T? {
        return delegate.read(input)?.apply {
            Reflection.createKotlinClass(type.rawType).memberProperties.forEach {
                if (!it.returnType.isMarkedNullable && it.get(this) == null) {
                    throw IllegalArgumentException("Value of non-nullable property [${it.name}] cannot be null")
                }
            }
        }
    }
}
