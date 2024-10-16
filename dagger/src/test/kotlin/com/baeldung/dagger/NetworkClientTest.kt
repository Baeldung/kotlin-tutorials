package com.baeldung.dagger

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NetworkClientUnitTest {
    private lateinit var networkClient: NetworkClientMultipleInstances

    @BeforeEach
    fun setUp() {
        val testComponent = DaggerTestAppComponent.create()
        networkClient = testComponent.networkClient()
    }

    @Test
    fun `test NetworkClient with mock dependencies`() {
        assertEquals("Mocked Debug Data", networkClient.getDebugData())
        assertEquals("Mocked Release Data", networkClient.getReleaseData())
    }
}