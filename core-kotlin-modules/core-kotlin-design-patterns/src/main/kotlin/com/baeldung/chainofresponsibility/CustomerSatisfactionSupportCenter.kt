package com.baeldung.chainofresponsibility

class CustomerSatisfactionSupportCenter(requestType: RequestType) : AbstractSupportCenterHandler(requestType) {

    override var nextHandler: AbstractSupportCenterHandler? = null
    override fun handleRequest(message: String): String  {
        return "Handler: CustomerSatisfactionSupportHandler - request: $message"
    }
}