package com.baeldung.composite


fun main() {

    val actionMoviesPlayList = Playlist("Action Movies")
    actionMoviesPlayList.add(Movie("The Matrix"))
    actionMoviesPlayList.add(Movie("Die Hard"))

    val comicMoviesPlayList = Playlist("Comic Movies")
    comicMoviesPlayList.add(Movie("The Hangover"))
    comicMoviesPlayList.add(Movie("Bridesmaids"))

    val allPlaylists = Playlist("All Playlists")
    allPlaylists.add(actionMoviesPlayList)
    allPlaylists.add(comicMoviesPlayList)

    val playResult = allPlaylists.play()

    val stopResult = allPlaylists.stop()

}
