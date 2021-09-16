package com.baeldung.kotlin.gson

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("article_title")
    var title: String,
    @SerializedName("article_category")
    var category: String,
    @SerializedName("article_views")
    var views: Int
) {
}