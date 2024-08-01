package com.baeldung.serialization

import com.baeldung.serialization.jackson.StatusSerializer
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @Serializable(with = StatusSerializer::class)
    val status: Status
)