package com.baeldung.chainofresponsibility

object SupportCenterClient {
    val handlerChain: AbstractSupportCenterHandler
        get() {
            val technical = TechnicalSupportCenter(AbstractSupportCenterHandler.Constants.TECHNICAL)
            val bills = BillsSupportCenter(AbstractSupportCenterHandler.Constants.BILL)
            val customerSatisfactionSupportCenter = CustomerSatisfactionSupportCenter(AbstractSupportCenterHandler.Constants.CUSTOMERSATISFACTION)

            technical.nextHandler(bills)
            bills.nextHandler(customerSatisfactionSupportCenter)

            return technical
        }

    var handler: AbstractSupportCenterHandler? = null

}