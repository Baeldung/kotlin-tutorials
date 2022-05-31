@file:Suppress("HasPlatformType")

package com.baeldung.kotlin.tomap

import com.baeldung.kotlin.tomap.MapHelper.DATE_FORMAT
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule

object JacksonMapHelper {

    val DEFAULT_JACKSON_MAPPER = ObjectMapper().registerModule(KotlinModule())

    val JACKSON_MAPPER_WITH_DATE_FORMAT = ObjectMapper().registerModule(KotlinModule()).apply {
        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        dateFormat = DATE_FORMAT
    }
}