package com.baeldung.late

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class LateInitUnitTest {

    private lateinit var answer: String

    @Test
    fun givenLateInit_WhenNotInitialized_ShouldThrowAnException() {
        assertFailsWith(UninitializedPropertyAccessException::class) {
            answer.length
        }
    }

    @Test
    fun givenLateInit_TheIsInitialized_ReturnsTheInitializationStatus() {
        assertFalse { this::answer.isInitialized }
        answer = "42"
        assertTrue { this::answer.isInitialized }
    }

    @Test
    fun givenLateInitChain_WhenInitialized_ReturnsTheInitializationStatusAsTrue() {
        val class1 = Class1()
        val class2 = Class2()
        val class3 = Class3()

        class2.class1 = class1
        class3.class2 = class2

        assertTrue(class2.isInitialized())
        assertTrue(class3.isInitialized())
    }

    @Test
    fun givenLateInitChain_WhenNotInitialized_ReturnsTheInitializationStatusAsFalse() {
        val class1 = Class1()
        val class2 = Class2()
        val class3 = Class3()

        assertNotNull(class1)
        assertFalse(class2.isInitialized())
        assertFalse(class3.isInitialized())
    }

    /*
    @Test
    fun givenLateInitChain_TheIsInitializedDirectAccess_GivesCompilationError() {
        val class1 = Class1()
        val class2 = Class2()
        val class3 = Class3()

        class2.class1 = class1
        class3.class2 = class2

        // class3.isInitializedDirectAccess()
    }
     */
}
