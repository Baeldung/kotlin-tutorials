package com.baeldung.chainofresponsibility

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ChainOfResponsibilityUnitTest {
    @Test
    fun `handlers processing the correct client request`(){
        SupportCenterClient.handlerChain.apply {

            val result1 = receiveRequest(AbstractSupportCenterHandler.Constants.TECHNICAL, "technical issue.")
            assertEquals("Handler: TechnicalSupportHandler - request: technical issue.", result1)

            val result2 = receiveRequest(AbstractSupportCenterHandler.Constants.BILL, "billing issue.")
            assertEquals("Handler: TechnicalSupportHandler - request: billing issue.", result2)

            val result3 = receiveRequest(AbstractSupportCenterHandler.Constants.CUSTOMERSATISFACTION, "customer satisfaction issue.")
            assertEquals("Handler: TechnicalSupportHandler - request: customer satisfaction issue.", result3)

        }
    }

    @Test
    fun `billing center attempt to process technical request`(){
        val technical = TechnicalSupportCenter(AbstractSupportCenterHandler.Constants.TECHNICAL)
        val bills = BillsSupportCenter(AbstractSupportCenterHandler.Constants.BILL)

        technical.nextHandler(bills)
        bills.nextHandler(technical)

        SupportCenterClient.handler = bills
        val result = (SupportCenterClient.handler as BillsSupportCenter).receiveRequest(AbstractSupportCenterHandler.Constants.TECHNICAL, "technical issue.")
        assertEquals("Handler: TechnicalSupportHandler - request: technical issue.", result)
    }

    @Test
    fun `no center can process client request`(){
        val bills = BillsSupportCenter(AbstractSupportCenterHandler.Constants.BILL)
        val customerSatisfactionSupportCenter = CustomerSatisfactionSupportCenter(AbstractSupportCenterHandler.Constants.CUSTOMERSATISFACTION)

        bills.nextHandler(customerSatisfactionSupportCenter)

        SupportCenterClient.handler = bills
        val result = (SupportCenterClient.handler as BillsSupportCenter).receiveRequest(AbstractSupportCenterHandler.Constants.TECHNICAL, "technical issue.")
        assertEquals("No next handler for this request", result)
    }

}