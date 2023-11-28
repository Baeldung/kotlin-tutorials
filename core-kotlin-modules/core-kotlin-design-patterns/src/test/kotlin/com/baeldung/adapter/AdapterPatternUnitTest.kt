package com.baeldung.adapter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class AdapterPatternUnitTest {
    @Test
    fun testAudioPlayer() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        val audioPlayer = AudioPlayer()

        audioPlayer.play("mp3", "fetch_water.mp3")
        assertEquals("Playing mp3 file. Name: fetch_water.mp3\n", outContent.toString())

        outContent.reset()
        audioPlayer.play("mp4", "get_lost.mp4")
        assertEquals("Playing mp4 file. Name: get_lost.mp4\n", outContent.toString())

        outContent.reset()
        audioPlayer.play("vlc", "life_lessons.vlc")
        assertEquals("Playing vlc file. Name: life_lessons.vlc\n", outContent.toString())

        outContent.reset()
        audioPlayer.play("avi", "still_waters.avi")
        assertEquals("Invalid media. avi format not supported\n", outContent.toString())
    }
}