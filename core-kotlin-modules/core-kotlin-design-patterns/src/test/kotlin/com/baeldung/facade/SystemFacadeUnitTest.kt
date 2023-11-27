package com.baeldung.facade


import org.junit.jupiter.api.Test
import org.mockito.Mockito.*


class SystemFacadeUnitTest {
    private val databaseHandler = mock(DatabaseHandler::class.java)
    private val businessLogicHandler = mock(BusinessLogicHandler::class.java)
    private val notificationHandler = mock(NotificationHandler::class.java)

    @Test
    fun `test performSystemTask executes necessary operations in correct order`() {
        `when`(databaseHandler.connect()).then {}
        `when`(businessLogicHandler.performTask()).then {}
        `when`(notificationHandler.sendNotification()).then {}

        val systemFacade = SystemFacade(databaseHandler, businessLogicHandler, notificationHandler)
        systemFacade.performSystemTask()

        inOrder(databaseHandler, businessLogicHandler, notificationHandler).apply {
            verify(databaseHandler).connect()
            verify(businessLogicHandler).performTask()
            verify(notificationHandler).sendNotification()
            verify(databaseHandler).disconnect()
        }
    }
}