package com.baeldung.chainofresponsibility

object SupportCenterClient {
    val handlerChain: AbstractSupportCenterHandler
        get() {
            val technical = TechnicalSupportCenter(AbstractSupportCenterHandler.Constants.TECHNICAL)
            val bills = BillsSupportCenter(AbstractSupportCenterHandler.Constants.Bill)
            val customerSatisfactionSupportCenter = CustomerSatisfactionSupportCenter(AbstractSupportCenterHandler.Constants.CustomerSatisfaction)

            technical.nextHandler(bills)
            bills.nextHandler(customerSatisfactionSupportCenter)

            return technical
        }

    var handler: AbstractSupportCenterHandler? = null

}