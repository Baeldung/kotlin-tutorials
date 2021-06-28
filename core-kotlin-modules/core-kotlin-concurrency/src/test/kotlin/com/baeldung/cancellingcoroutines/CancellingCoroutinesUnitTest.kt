package com.baeldung.cancellingcoroutines

import kotlinx.coroutines.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CancellingCoroutinesUnitTest {

    @Test
    fun givenParentWithTwoChildren_whenCancellingParent_thenChildrenGetCancelled() {
        runBlocking {
            var child1: Job? = null
            var child2: Job? = null
            val parent = launch(Dispatchers.Default) {
                child1 = launch { keepPrinting("- child1") }
                child2 = launch { keepPrinting("- child2") }
                keepPrinting("parent")
            }
            delay(300)

            parent.cancelAndJoin()

            assertThat(parent.isActive).isFalse
            assertThat(child1!!.isActive).isFalse
            assertThat(child2!!.isActive).isFalse
        }
    }

    @Test
    fun givenParentWithTwoChildren_whenCancellingChild_thenParentAndRemainingChildKeepsRunning() {
        runBlocking {
            var child1: Job? = null
            var child2: Job? = null
            val parent = launch(Dispatchers.Default) {
                child1 = launch {
                    keepPrinting("- child1")
                }
                child2 = launch {
                    keepPrinting("- child2")
                }
                keepPrinting("parent")
            }
            delay(100)

            child1!!.cancel()

            assertThat(parent.isActive).isTrue
            assertThat(child1!!.isActive).isFalse
            assertThat(child2!!.isActive).isTrue
        }
    }

    private suspend fun keepPrinting(text: String) {
        repeat(10) {
            println("$it $text")
            delay(50)
        }
    }
}
