package com.baeldung.dataclasstobytebuffer

import java.io.*
import java.nio.ByteBuffer

data class UserInputOutputStream(val id: Int, val name: String, val age: Int) : Serializable

fun UserInputOutputStream.outputStreamByteBuffer(): ByteBuffer {
    val byteArrayOutputStream = ByteArrayOutputStream()
    val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)

    objectOutputStream.writeObject(this)
    objectOutputStream.flush()

    val byteArray = byteArrayOutputStream.toByteArray()
    return ByteBuffer.wrap(byteArray)
}

fun ByteBuffer.inputStreamToUser(): UserInputOutputStream {
    val byteArray = ByteArray(this.remaining())
    this.get(byteArray)

    val byteArrayInputStream = ByteArrayInputStream(byteArray)
    val objectInputStream = ObjectInputStream(byteArrayInputStream)

    return objectInputStream.readObject() as UserInputOutputStream
}