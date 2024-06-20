package com.baeldung.serialization

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue

enum class Status {
    SUCCESS,
    ERROR,
    @JsonEnumDefaultValue // Jackson-specific annotation
    UNKNOWN
}