package com.baeldung.backingfield

data class HttpResponse(val body: String, var headers: Map<String, String>) {
    val hasBody: Boolean
        get() = body.isNotBlank()
    var statusCode: Int = 100
        set(value) {
            if (value in 100..599) field = value
        }
}
