package com.baeldung.chainofresponsibility

abstract class AbstractSupportCenterHandler(private val level: Role) {
    enum class Role {
        TECHNICAL, BILL, CUSTOMER_SATISFACTION, UNKNOWN
    }

    open var nextHandler: AbstractSupportCenterHandler? = null

    open fun nextHandler(handler: AbstractSupportCenterHandler) {
        this.nextHandler = handler
    }

    open fun receiveRequest(level: Role, message: String): String? {
        when (this.level == level) {
            true -> return handleRequest(message)
            else -> return nextHandler?.receiveRequest(level, message)
                ?: return "No next handler for this request"
        }
    }
    protected abstract fun handleRequest(message: String): String?
}