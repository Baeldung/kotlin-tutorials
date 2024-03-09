@file:JvmName("MessageHelper")
@file:JvmMultifileClass //used
package com.baeldung.jvmannotations

import java.util.*

@JvmName("getMyUsername")
fun getMyName() : String {
    return "myUserId"
}

object MessageBroker {
    @JvmStatic
    var totalMessagesSent = 0

    const val maxMessageLength = 0

    @JvmStatic
    fun clearAllMessages() {
    }

    @JvmStatic
    @JvmOverloads
    @Throws(Exception::class)
    fun findMessages(sender : String, type : String = "text", maxResults : Int = 10) : List<Message> {
        println("Input params. Type: $type, maxResults: $maxResults")
        if(sender.isEmpty()) {
            throw Exception()
        }
        return ArrayList()
    }
}

class Message {

    // this would cause a compilation error since sender is immutable
    // @set:JvmName("setSender")
    val sender = "myself"

    // this works as name is overridden
    @JvmName("getSenderName")
    fun getSender() : String = "from:$sender"

    @get:JvmName("getReceiverName")
    @set:JvmName("setReceiverName")
    var receiver : String = ""

    @get:JvmName("getContent")
    @set:JvmName("setContent")
    var text = ""

    @get:JvmName("getId")
    val id = 0

    @get:JvmName("hasAttachment")
    var hasAttachment = true

    var isEncrypted = true

    fun setReceivers(receiverNames : List<String>) {
        println("Set receiverNames, value: $receiverNames")
    }

    @JvmName("setReceiverIds")
    fun setReceivers(receiverNames : List<Int>) {
        println("Set receiverIds, value: $receiverNames")
    }
}