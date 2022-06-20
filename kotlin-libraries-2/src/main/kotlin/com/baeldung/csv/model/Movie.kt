package com.baeldung.csv.model

import java.time.Year

data class Movie(
    val year: Year,
    val score: Int,
    val title: String,
)