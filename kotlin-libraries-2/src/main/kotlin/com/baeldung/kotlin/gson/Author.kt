package com.baeldung.kotlin.gson

data class Author(
    var name: String,
    var type: String? = null,
    var articles: List<Article>? = null,
) {
}