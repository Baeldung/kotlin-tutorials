package com.baeldung.testingStateFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

internal class DataTransformer(scope: CoroutineScope) {
    private val _dataFlow = MutableSharedFlow<String>()  // Simulates a repository stream

    val transformedData = _dataFlow
        .map { it.uppercase() }
        .stateIn(
            scope = scope,
            started = SharingStarted.Eagerly,
            initialValue = "INITIAL"
        )

    suspend fun emit(value: String) {
        _dataFlow.emit(value)
    }
}
