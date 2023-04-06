package com.baeldung.coroutine

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class SuspendCoroutineUnitTest {
    @Test
    fun when_using_callback_version_then_it_looks_the_usual() = runBlocking {
        loadData { result ->
            result.onFailure {
                assertNotNull(it)
                it.printStackTrace(System.out)
            }
                .onSuccess {
                    assertNotNull(it)
                    println(it)
                }
        }

    }

    @Test
    fun when_using_coroutine_version_then_nothing_is_changed() = runBlocking {
        loadDataSequential { result ->
            result.onFailure {
                assertNotNull(it)
                it.printStackTrace(System.out)
            }
                .onSuccess {
                    assertNotNull(it)
                    println(it)
                }
        }

    }
}
