package com.baeldung.argumentcaptor

class MessageDispatcher(private val messageService: IMessageService): IMessageDispatcher {

    override fun sendMessage(msg: String, done: (result: Boolean) -> Unit) {

        val result = this.messageService.send(msg)

        done(result)
    }
}

interface IMessageDispatcher {

    fun sendMessage(msg: String, done: (result: Boolean) -> Unit)
}

interface IMessageService {

    fun send(msg: String): Boolean
}
