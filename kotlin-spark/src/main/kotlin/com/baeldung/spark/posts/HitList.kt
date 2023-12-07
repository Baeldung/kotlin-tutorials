package com.baeldung.spark.posts

data class HitList<T>(
    val entries: List<T>,
    val total: Int
)