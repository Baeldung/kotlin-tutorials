package com.baeldung.testingStateFlows

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class StateFlowExample {
    private val _state = MutableStateFlow("Initial State")
    val state: StateFlow<String> = _state.asStateFlow()

    fun getCurrentState() = state.value

    fun updateState(newValue: String) {
        _state.value = newValue
    }
}
