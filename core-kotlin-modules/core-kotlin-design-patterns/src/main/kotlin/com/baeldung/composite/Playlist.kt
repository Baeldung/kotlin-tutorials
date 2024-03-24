package com.baeldung.composite

class Playlist(private val name: String) : MovieComponent {
    private val movieComponents = mutableListOf<MovieComponent>()

    fun add(movieComponent: MovieComponent) {
        movieComponents.add(movieComponent)
    }

    fun remove(movieComponent: MovieComponent) {
        movieComponents.remove(movieComponent)
    }

    override fun play(): String {
        val result = StringBuilder()
        result.append("Playing playlist: $name\n")
        for (movieComponent in movieComponents) {
            result.append(movieComponent.play())
        }
        return result.toString()
    }
}