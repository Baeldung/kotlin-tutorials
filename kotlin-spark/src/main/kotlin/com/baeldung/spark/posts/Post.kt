package com.baeldung.spark.posts

import java.time.Instant

data class Post(
    val id: String,
    val posterId: String,
    val posted: Instant,

    val content: String,
    val likes: Int
)