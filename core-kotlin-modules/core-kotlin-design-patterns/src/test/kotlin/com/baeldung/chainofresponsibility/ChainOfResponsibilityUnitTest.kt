package com.baeldung.chainofresponsibility

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ChainOfResponsibilityUnitTest {
    @Test
    fun `support center attempt to process technical request`(){
        val result1 = SupportCenterClient.technicalHandler.receiveRequest(AbstractSupportCenterHandler.RequestType.TECHNICAL, "technical issue.")
        assertEquals("Handler: TechnicalSupportHandler - request: technical issue.", result1)
    }

    @Test
    fun `support center attempt to process billing request`(){
        val result2 = SupportCenterClient.technicalHandler.receiveRequest(AbstractSupportCenterHandler.RequestType.BILL, "billing issue.")
        assertEquals("Handler: BillsSupportHandler - request: billing issue.", result2)
    }

    @Test
    fun `support center attempt to process customer happiness request`(){
        val result3 = SupportCenterClient.technicalHandler.receiveRequest(AbstractSupportCenterHandler.RequestType.CUSTOMER_SATISFACTION, "customer satisfaction issue.")
        assertEquals("Handler: CustomerSatisfactionSupportHandler - request: customer satisfaction issue.", result3)
    }

    @Test
    fun `no center can process client request`(){
        val result = SupportCenterClient.technicalHandler.receiveRequest(AbstractSupportCenterHandler.RequestType.UNKNOWN, "Other issue.")
        assertEquals("No next handler for this request", result)
    }
}