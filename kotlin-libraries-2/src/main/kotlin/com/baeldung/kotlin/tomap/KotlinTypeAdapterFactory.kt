package com.baeldung.kotlin.tomap

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken

class KotlinTypeAdapterFactory : TypeAdapterFactory {
    override fun <T : Any> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
        val delegate = gson.getDelegateAdapter(this, type)
        if (type.rawType.declaredAnnotations.none { it.annotationClass == Metadata::class }) {
            return null
        }
        return KotlinTypeAdaptor(delegate, type)
    }
}
