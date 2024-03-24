package com.baeldung.composite

class Movie(private val name: String) : MovieComponent {
    override fun play(): String {
        return "Playing movie: $name\n"
    }
}