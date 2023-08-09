package com.baeldung.adapter

class AudioPlayer : MediaPlayer {
    private var mediaAdapter: MediaAdapter? = null

    override fun play(audioType: String, fileName: String) {
        if (audioType.equals("mp3", true)) {
            println("Playing mp3 file. Name: $fileName")
        } else if (audioType.equals("vlc", true) || audioType.equals("mp4", true)) {
            mediaAdapter = MediaAdapter(audioType)
            mediaAdapter?.play(audioType, fileName)
        } else {
            println("Invalid media. $audioType format not supported")
        }
    }
}