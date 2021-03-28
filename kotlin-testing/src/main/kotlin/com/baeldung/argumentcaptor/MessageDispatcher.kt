package com.baeldung.argumentcaptor

class MessageDispatcher(private val messageService: IMessageService) {

    fun sendMessage(msg: String, done: (result: Boolean) -> Unit) {

        val result = this.messageService.send(msg)

        done(result)
    }
}

interface IMessageService {

    fun send(msg: String): Boolean
}
