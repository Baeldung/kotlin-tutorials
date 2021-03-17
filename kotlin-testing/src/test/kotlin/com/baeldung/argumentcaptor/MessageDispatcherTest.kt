package com.baeldung.argumentcaptor

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.jupiter.api.Test

class MessageDispatcherTest {

    private val messageService: IMessageService = mock()
    private val messageDispatcher = MessageDispatcher(messageService)

    @Test
    fun `given a message and lambda, when message send succeeds, then lambda called with true`() {
        val msg = "hello world"
        whenever(messageService.send(msg)).thenReturn(true)

        val myFun: (Boolean) -> Unit = mock()
        messageDispatcher.sendMessage(msg, myFun)

        verify(messageService).send(msg)
        verify(myFun).invoke(true)
    }

    @Test
    fun `given a message and lambda, when message send fails, then lambda called with false`() {
        val msg = "hello world"
        whenever(messageService.send(msg)).thenReturn(false)

        val myFun: (Boolean) -> Unit = mock()
        messageDispatcher.sendMessage(msg, myFun)

        verify(messageService).send(msg)
        verify(myFun).invoke(false)
    }
}