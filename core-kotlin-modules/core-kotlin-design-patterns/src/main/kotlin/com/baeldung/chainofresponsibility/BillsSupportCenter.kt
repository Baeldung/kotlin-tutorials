package com.baeldung.chainofresponsibility

class BillsSupportCenter(level: Int) : AbstractSupportCenterHandler(level) {
    override fun handleRequest(message: String): String {
        return "Handler: BillsSupportCenter - request: $message"
    }
}