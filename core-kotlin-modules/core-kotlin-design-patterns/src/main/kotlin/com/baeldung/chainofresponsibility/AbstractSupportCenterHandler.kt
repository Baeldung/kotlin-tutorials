package com.baeldung.chainofresponsibility

abstract class AbstractSupportCenterHandler(private val level: Int) {
    object Constants {
        val TECHNICAL: Int = 1
        val Bill: Int = 2
        val CustomerSatisfaction: Int = 3
    }

    private var nextHandler: AbstractSupportCenterHandler? = null

    open fun nextHandler(handler: AbstractSupportCenterHandler) {
        this.nextHandler = handler
    }

    open fun receiveRequest(level: Int, message: String): String {
        when (this.level <= level) {
            true -> return handleRequest(message)
            else -> return nextHandler?.receiveRequest(level, message)
                ?: return "No next handler for this request"
        }
    }

    protected abstract fun handleRequest(message: String): String
}