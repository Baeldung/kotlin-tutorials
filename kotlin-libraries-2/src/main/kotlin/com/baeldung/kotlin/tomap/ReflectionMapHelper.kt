package com.baeldung.kotlin.tomap

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

@Suppress("UNCHECKED_CAST")
object ReflectionMapHelper {
    fun <T : Any> toMap(obj: T): Map<String, Any?> {
        return (obj::class as KClass<T>).memberProperties.associate { prop ->
            prop.name to prop.get(obj)?.let { value ->
                if (value::class.isData) {
                    toMap(value)
                } else {
                    value
                }
            }
        }
    }

    fun <T : Any> toMapWithOnlyPrimaryConstructorProperties(obj: T): Map<String, Any?> {
        val kClass = obj::class as KClass<T>
        val primaryConstructorPropertyNames = kClass.primaryConstructor?.parameters?.map { it.name } ?: run {
            return toMap(obj)
        }
        return kClass.memberProperties.mapNotNull { prop ->
            prop.name.takeIf { it in primaryConstructorPropertyNames }?.let {
                it to prop.get(obj)?.let { value ->
                    if (value::class.isData) {
                        toMap(value)
                    } else {
                        value
                    }
                }
            }
        }.toMap()
    }
}