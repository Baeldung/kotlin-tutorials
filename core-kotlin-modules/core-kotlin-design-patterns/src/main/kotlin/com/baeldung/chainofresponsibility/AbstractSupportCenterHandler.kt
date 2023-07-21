package com.baeldung.chainofresponsibility

abstract class AbstractSupportCenterHandler(private val level: Constants) {
    enum class Constants {
        TECHNICAL, BILL, CUSTOMERSATISFACTION
    }

    private var nextHandler: AbstractSupportCenterHandler? = null

    open fun nextHandler(handler: AbstractSupportCenterHandler) {
        this.nextHandler = handler
    }

    open fun receiveRequest(level: Constants, message: String): String? {
        when (this.level <= level) {
            true -> return handleRequest(message)
            else -> return nextHandler?.receiveRequest(level, message)
                ?: return "No next handler for this request"
        }
    }

    protected abstract fun handleRequest(message: String): String?
}