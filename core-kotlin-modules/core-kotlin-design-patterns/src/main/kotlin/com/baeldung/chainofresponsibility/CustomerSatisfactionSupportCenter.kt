package com.baeldung.chainofresponsibility

class CustomerSatisfactionSupportCenter(level: Role) : AbstractSupportCenterHandler(level) {

    override var nextHandler: AbstractSupportCenterHandler? = null
    override fun handleRequest(message: String): String  {
        return "Handler: CustomerSatisfactionSupportHandler - request: $message"
    }
}