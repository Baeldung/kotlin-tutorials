package com.baeldung.chainofresponsibility

class CustomerSatisfactionSupportCenter(level: Constants) : AbstractSupportCenterHandler(level) {
    override fun handleRequest(message: String): String  {
        return "Handler: AdvanceSupportCenter - request: $message"
    }
}