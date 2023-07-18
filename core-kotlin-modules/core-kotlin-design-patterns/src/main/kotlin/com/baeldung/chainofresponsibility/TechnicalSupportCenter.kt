package com.baeldung.chainofresponsibility

class TechnicalSupportCenter(level: Int) : AbstractSupportCenterHandler(level) {
    override fun handleRequest(message: String): String {
        return "Handler: TechnicalSupportHandler - request: $message"
    }
}