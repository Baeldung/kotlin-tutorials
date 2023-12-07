package com.baeldung.chainofresponsibility

abstract class AbstractSupportCenterHandler(private val requestType: RequestType) {
    enum class RequestType {
        TECHNICAL, BILL, CUSTOMER_SATISFACTION, UNKNOWN
    }

    open var nextHandler: AbstractSupportCenterHandler? = null

    open fun nextHandler(handler: AbstractSupportCenterHandler) {
        this.nextHandler = handler
    }

    open fun receiveRequest(requestType: RequestType, message: String): String? {
        when (this.requestType == requestType) {
            true -> return handleRequest(message)
            else -> return nextHandler?.receiveRequest(requestType, message)
                ?: return "No next handler for this request"
        }
    }
    protected abstract fun handleRequest(message: String): String?
}