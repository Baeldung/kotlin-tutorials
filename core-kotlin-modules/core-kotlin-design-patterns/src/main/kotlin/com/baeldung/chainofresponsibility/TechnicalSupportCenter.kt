package com.baeldung.chainofresponsibility

class TechnicalSupportCenter(level: Role) : AbstractSupportCenterHandler(level) {

    override var nextHandler: AbstractSupportCenterHandler? = BillsSupportCenter(Role.BILL)
    override fun handleRequest(message: String): String {
        return "Handler: TechnicalSupportHandler - request: $message"
    }
}