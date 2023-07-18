package com.baeldung.chainofresponsibility

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ChainOfResponsibilityUnitTest {
    @Test
    fun `handlers processing the correct client request`(){
        SupportCenterClient.handlerChain.apply {

            val result1 = receiveRequest(AbstractSupportCenterHandler.Constants.TECHNICAL, "technical issue.")
            assertEquals("Handler: TechnicalSupportHandler - request: technical issue.", result1)

            val result2 = receiveRequest(AbstractSupportCenterHandler.Constants.Bill, "billing issue.")
            assertEquals("Handler: TechnicalSupportHandler - request: billing issue.", result2)

            val result3 = receiveRequest(AbstractSupportCenterHandler.Constants.CustomerSatisfaction, "customer satisfaction issue.")
            assertEquals("Handler: TechnicalSupportHandler - request: customer satisfaction issue.", result3)

        }
    }

    @Test
    fun `billing center attempt to process technical request`(){
        val technical = TechnicalSupportCenter(AbstractSupportCenterHandler.Constants.TECHNICAL)
        val bills = BillsSupportCenter(AbstractSupportCenterHandler.Constants.Bill)

        technical.nextHandler(bills)
        bills.nextHandler(technical)

        SupportCenterClient.handler = bills
        val result = (SupportCenterClient.handler as BillsSupportCenter).receiveRequest(AbstractSupportCenterHandler.Constants.TECHNICAL, "technical issue.")
        assertEquals("Handler: TechnicalSupportHandler - request: technical issue.", result)
    }

    @Test
    fun `no center can process client request`(){
        val bills = BillsSupportCenter(AbstractSupportCenterHandler.Constants.Bill)
        val customerSatisfactionSupportCenter = CustomerSatisfactionSupportCenter(AbstractSupportCenterHandler.Constants.CustomerSatisfaction)

        bills.nextHandler(customerSatisfactionSupportCenter)

        SupportCenterClient.handler = bills
        val result = (SupportCenterClient.handler as BillsSupportCenter).receiveRequest(AbstractSupportCenterHandler.Constants.TECHNICAL, "technical issue.")
        assertEquals("No next handler for this request", result)
    }

}