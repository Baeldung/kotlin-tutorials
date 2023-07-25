package com.baeldung.chainofresponsibility

object SupportCenterClient {
    val technicalHandler = TechnicalSupportCenter(AbstractSupportCenterHandler.RequestType.TECHNICAL)
}