package com.baeldung.mockito.argumentcaptor

import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class CommandExecutor(
    private val registry: CommandRegistry,
    private val writer: CommandWriter,
) {
    fun executeCommand(command: String) {
        registry.storeCommand(command) { success ->
            success && writer.writeCommand(command)
        }
    }
}

interface CommandRegistry {
    fun storeCommand(command: String, onFinish: (success: Boolean) -> Unit)
}

interface CommandWriter {
    fun writeCommand(command: String): Boolean
}

class CommandExecutorUnitTest {

    @Test
    fun `when command is executed it is stored in registry`() {
        // given
        val command = "CMD_01"

        val registryMock = mock<CommandRegistry>()
        val commandExecutor = CommandExecutor(registryMock, mock())

        // when
        commandExecutor.executeCommand(command)

        // then
        verify(registryMock).storeCommand(eq(command), any())
    }

    @Test
    fun `when command is executed and registry max size is not reached it is not dumped to a file`() {
        // given
        val command = "CMD_01"

        val registryMock = mock<CommandRegistry>()
        val writerMock = mock<CommandWriter>()
        val commandExecutor = CommandExecutor(registryMock, writerMock)

        // when
        commandExecutor.executeCommand(command)

        // then
        val captor = argumentCaptor<(Boolean) -> Unit>()

        verify(registryMock).storeCommand(any(), captor.capture())

        val capturedOperation = captor.firstValue

        capturedOperation.invoke(false)
        verify(writerMock, times(0)).writeCommand(any())
    }

    @Test
    fun `when command is executed and registry max size is reached it is dumped to a file`() {
        // given
        val command = "CMD_01"

        val registryMock = mock<CommandRegistry>()
        val writerMock = mock<CommandWriter>()
        val commandExecutor = CommandExecutor(registryMock, writerMock)

        // when
        commandExecutor.executeCommand(command)

        // then
        val captor = argumentCaptor<(Boolean) -> Unit>()

        verify(registryMock).storeCommand(any(), captor.capture())

        val capturedOperation = captor.firstValue

        capturedOperation.invoke(true)
        verify(writerMock, times(1)).writeCommand(any())
    }
}