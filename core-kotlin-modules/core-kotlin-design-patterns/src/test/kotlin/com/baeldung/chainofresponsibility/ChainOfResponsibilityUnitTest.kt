package com.baeldung.chainofresponsibility

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ChainOfResponsibilityUnitTest {
    @Test
    fun `handlers processing the correct client request`(){
        val result1 = SupportCenterClient.technicalHandler.receiveRequest(AbstractSupportCenterHandler.Role.TECHNICAL, "technical issue.")
        assertEquals("Handler: TechnicalSupportHandler - request: technical issue.", result1)

        val result2 = SupportCenterClient.billsHandler.receiveRequest(AbstractSupportCenterHandler.Role.BILL, "billing issue.")
        assertEquals("Handler: BillsSupportHandler - request: billing issue.", result2)

        val result3 = SupportCenterClient.customerSatisfactionHandler.receiveRequest(AbstractSupportCenterHandler.Role.CUSTOMER_SATISFACTION, "customer satisfaction issue.")
        assertEquals("Handler: CustomerSatisfactionSupportHandler - request: customer satisfaction issue.", result3)
    }

    @Test
    fun `technical center attempt to process billing request`(){
        val result = SupportCenterClient.technicalHandler.receiveRequest(AbstractSupportCenterHandler.Role.BILL, "billing issue.")
        assertEquals("Handler: BillsSupportHandler - request: billing issue.", result)
    }

    @Test
    fun `Billing center attempt to process customer satisfaction request`(){
        val result = SupportCenterClient.billsHandler.receiveRequest(AbstractSupportCenterHandler.Role.CUSTOMER_SATISFACTION, "customer satisfaction issue.")
        assertEquals("Handler: CustomerSatisfactionSupportHandler - request: customer satisfaction issue.", result)
    }

    @Test
    fun `no center can process client request`(){
        val result = SupportCenterClient.technicalHandler.receiveRequest(AbstractSupportCenterHandler.Role.UNKNOWN, "Other issue.")
        assertEquals("No next handler for this request", result)
    }
}