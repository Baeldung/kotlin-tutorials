package com.baeldung.chainofresponsibility

class TechnicalSupportCenter(requestType: RequestType) : AbstractSupportCenterHandler(requestType) {

    override var nextHandler: AbstractSupportCenterHandler? = BillsSupportCenter(RequestType.BILL)
    override fun handleRequest(message: String): String {
        return "Handler: TechnicalSupportHandler - request: $message"
    }
}