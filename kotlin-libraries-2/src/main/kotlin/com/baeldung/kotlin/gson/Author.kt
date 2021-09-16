package com.baeldung.kotlin.gson

data class Author(
    var name: String,
    var type: String? = null,
    var topics: List<String>? = null,
    var contact : Contact? = null
) {
}