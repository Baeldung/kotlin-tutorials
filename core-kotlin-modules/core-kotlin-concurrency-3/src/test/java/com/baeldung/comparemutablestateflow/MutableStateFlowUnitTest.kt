package com.baeldung.comparemutablestateflow

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
class MutableStateFlowUnitTest {
    @Test
    fun `initial value should be Baeldung Team`() {
        val mutableStateFlow = MutableStateFlow("Baeldung Team")
        val currentValue = mutableStateFlow.value
        assertEquals("Baeldung Team", currentValue)
    }
    @Test
    fun `emit function should update the value`() = runBlocking {
        val mutableStateFlow = MutableStateFlow("Baledung Team")
        mutableStateFlow.emit("Baledung Team Kotlin")
        assertEquals("Baledung Team Kotlin", mutableStateFlow.value)
    }
//    @Test
//    fun `tryEmit function should update the value`() {
//        val mutableStateFlow = MutableStateFlow("Baledung Team Kotlin 2024")
//        val emitResult = mutableStateFlow.tryEmit("Baledung Team Kotlin 2024")
//        assertTrue(emitResult)
//        assertEquals("Baledung Team Kotlin 2024", mutableStateFlow.value)
//    }
}