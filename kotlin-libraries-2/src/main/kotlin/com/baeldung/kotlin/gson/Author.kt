package com.baeldung.kotlin.gson

import com.google.gson.annotations.SerializedName

data class Author(
    var name: String,
    var type: String? = null,
    @SerializedName("author_articles")
    var articles: List<Article>? = null,
) {
}