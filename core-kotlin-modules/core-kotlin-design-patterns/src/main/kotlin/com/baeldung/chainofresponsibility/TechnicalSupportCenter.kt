package com.baeldung.chainofresponsibility

class TechnicalSupportCenter(level: Constants) : AbstractSupportCenterHandler(level) {
    override fun handleRequest(message: String): String {
        return "Handler: TechnicalSupportHandler - request: $message"
    }
}