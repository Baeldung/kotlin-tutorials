@file:Suppress("HasPlatformType")

package com.baeldung.kotlin.tomap

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import kotlin.jvm.internal.Reflection
import kotlin.reflect.full.memberProperties

object GsonMapHelper {

    private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

    val GSON_MAPPER = GsonBuilder().serializeNulls().setDateFormat(DATE_FORMAT).create()

    val KOTLIN_GSON_MAPPER = GsonBuilder()
        .serializeNulls()
        .setDateFormat(DATE_FORMAT)
        .registerTypeAdapterFactory(KotlinTypeAdapterFactory())
        .create()

    class KotlinTypeAdapterFactory : TypeAdapterFactory {

        override fun <T : Any> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
            val delegate = gson.getDelegateAdapter(this, type)
            if (type.rawType.declaredAnnotations.none { it.annotationClass == Metadata::class }) {
                return null
            }

            return object : TypeAdapter<T>() {

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
        }
    }
}

