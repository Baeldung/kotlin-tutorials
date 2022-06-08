@file:Suppress("HasPlatformType")

package com.baeldung.kotlin.tomap

import com.google.gson.GsonBuilder

object GsonMapHelper {

    private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

    val GSON_MAPPER = GsonBuilder().serializeNulls().setDateFormat(DATE_FORMAT).create()

    val KOTLIN_GSON_MAPPER = GsonBuilder()
        .serializeNulls()
        .setDateFormat(DATE_FORMAT)
        .registerTypeAdapterFactory(KotlinTypeAdapterFactory())
        .create()

}

