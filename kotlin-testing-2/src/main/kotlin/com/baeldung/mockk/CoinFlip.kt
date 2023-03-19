package com.baeldung.mockk

fun coinFlip() = if(RandomNumberGenerator.random() < 0.5) "heads" else "tails"
