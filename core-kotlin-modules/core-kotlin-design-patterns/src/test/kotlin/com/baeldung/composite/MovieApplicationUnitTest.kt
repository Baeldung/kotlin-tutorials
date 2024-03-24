package com.baeldung.composite

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MovieApplicationUnitTest {

    @Test
    fun `should play movie`() {
        val movie = Movie("The Matrix")
        val result = movie.play()

        assertEquals("Playing movie: The Matrix\n", result)
    }

    @Test
    fun `should play playlist`() {
        val actionMoviesPlayList = Playlist("Action Movies")
        actionMoviesPlayList.add(Movie("The Matrix"))
        actionMoviesPlayList.add(Movie("Die Hard"))

        val comicMoviesPlayList = Playlist("Comic Movies")
        comicMoviesPlayList.add(Movie("The Hangover"))
        comicMoviesPlayList.add(Movie("Bridesmaids"))

        val allPlaylists = Playlist("All Playlists")
        allPlaylists.add(actionMoviesPlayList)
        allPlaylists.add(comicMoviesPlayList)

        val result = allPlaylists.play()

        assertEquals("Playing playlist: All Playlists\n" +
                "Playing playlist: Action Movies\n" +
                "Playing movie: The Matrix\n" +
                "Playing movie: Die Hard\n" +
                "Playing playlist: Comic Movies\n" +
                "Playing movie: The Hangover\n" +
                "Playing movie: Bridesmaids\n", result)
    }
}