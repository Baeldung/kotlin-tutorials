package com.baeldung.kotlin.anonymousObjects

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

abstract class Doc(val title: String, val author: String, var words: Long = 0L) {
    abstract fun summary(): String
}

interface Printable {
    val content: String
    fun print(): String
}

/**
 * If we use an anonymous object as the return value of a private method,
 * its members can still be accessed
 */
class PlayerService() {
    private fun giveMeAPlayer() = object {
        val name = "Kai"
        val gamePlayed = 6L
        val points = 42L
        fun pointsPerGame() = "$name: AVG points per Game: ${points / gamePlayed}"
    }

    fun getTheName(): String {
        val thePlayer = giveMeAPlayer()
        print(thePlayer.pointsPerGame())
        return thePlayer.name
    }
}

class AnonymousObjectsUnitTest {
    @Test
    fun `Given an abstract class when instantiating with object, should get an instance`() {
//        val article = Doc(title = "A nice article", author = "Kai", words = 420) //won't compile
        val article = object : Doc(title = "A nice article", author = "Kai", words = 420) {
            override fun summary() = "Title: <$title> ($words words) By $author"
        }
        article.let {
            assertThat(it).isInstanceOf(Doc::class.java)
            assertThat(it.summary()).isEqualTo("Title: <A nice article> (420 words) By Kai")
        }
    }

    @Test
    fun `Given an interface when instantiating with object, should get an instance`() {
        val sentence = object : Printable {
            override val content: String = "A beautiful sentence."
            override fun print(): String = "[Print Result]\n$content"
        }
        sentence.let {
            assertThat(it).isInstanceOf(Printable::class.java)
            assertThat(it.print()).isEqualTo("[Print Result]\nA beautiful sentence.")
        }
    }

    @Test
    fun `When instantiating with object from scratch, should get an anonymous object`() {
        val player = object {
            val name = "Kai"
            val gamePlayed = 6L
            val points = 42L
            fun pointsPerGame() = "$name: AVG points per Game: ${points / gamePlayed}"
        }

        player.let {
            assertThat(it.name).isEqualTo("Kai")
            assertThat(it.pointsPerGame()).isEqualTo("Kai: AVG points per Game: 7")
        }
    }

    @Test
    fun `When using anonymous object as the return type of a private method, should get expected value`() {
        val playerService = PlayerService()
        assertThat(playerService.getTheName()).isEqualTo("Kai")
    }
}





