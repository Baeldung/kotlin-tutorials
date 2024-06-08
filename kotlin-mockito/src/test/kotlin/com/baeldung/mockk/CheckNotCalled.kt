package com.baeldung.mockk

import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class CheckNotCalled {

    @Test
    fun `Verify a mock was not called`() {
        val myMock: Runnable = mockk()
        every { myMock.run() } answers { throw RuntimeException() }

        verify(exactly = 0) { myMock.run() }
        verify { myMock wasNot Called }
    }
}