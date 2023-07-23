package com.baeldung.chainofresponsibility

class BillsSupportCenter(level: Role) : AbstractSupportCenterHandler(level) {
    override var nextHandler: AbstractSupportCenterHandler? = CustomerSatisfactionSupportCenter(Role.CUSTOMER_SATISFACTION)
    override fun handleRequest(message: String): String {
        return "Handler: BillsSupportHandler - request: $message"
    }
}