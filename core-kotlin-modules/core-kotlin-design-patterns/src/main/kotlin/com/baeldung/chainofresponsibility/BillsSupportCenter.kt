package com.baeldung.chainofresponsibility

class BillsSupportCenter(level: Constants) : AbstractSupportCenterHandler(level) {
    override fun handleRequest(message: String): String {
        return "Handler: BillsSupportCenter - request: $message"
    }
}