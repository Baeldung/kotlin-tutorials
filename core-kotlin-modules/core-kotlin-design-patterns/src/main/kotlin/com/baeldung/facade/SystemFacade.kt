package com.baeldung.facade

open class SystemFacade(private val databaseHandler: DatabaseHandler, private val businessLogicHandler: BusinessLogicHandler, private val notificationHandler: NotificationHandler) {

    fun performSystemTask() {
        databaseHandler.connect()
        businessLogicHandler.performTask()
        notificationHandler.sendNotification()
        databaseHandler.disconnect()
    }
}