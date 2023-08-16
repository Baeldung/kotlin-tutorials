package com.baeldung.adapter

class Mp4Player : AdvancedMediaPlayer {
    override fun playVlc(fileName: String) {
    }

    override fun playMp4(fileName: String) {
        println("Playing mp4 file. Name: $fileName")
    }
}