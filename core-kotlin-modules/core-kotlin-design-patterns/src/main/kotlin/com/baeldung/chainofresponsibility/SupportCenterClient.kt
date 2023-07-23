package com.baeldung.chainofresponsibility

object SupportCenterClient {
    val technicalHandler = TechnicalSupportCenter(AbstractSupportCenterHandler.Role.TECHNICAL)
    val billsHandler = BillsSupportCenter(AbstractSupportCenterHandler.Role.BILL)
    val customerSatisfactionHandler = CustomerSatisfactionSupportCenter(AbstractSupportCenterHandler.Role.CUSTOMER_SATISFACTION)
}