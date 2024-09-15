package com.baeldung.dataclasstobytebuffer

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

data class User(val id: Int = 0, val name: String = "", val age: Int = 0)

fun User.manualToByteBuffer(): ByteBuffer {
    val nameBytes = this.name.toByteArray(Charsets.UTF_8)
    val buffer = ByteBuffer.allocate(Int.SIZE_BYTES + nameBytes.size + Int.SIZE_BYTES)

    buffer.putInt(this.id)
    buffer.put(nameBytes)
    buffer.putInt(this.age)

    buffer.flip() // Prepare the buffer for reading

    return buffer
}

fun ByteBuffer.manualToUser(): User {
    val id = this.int
    val nameBytes = ByteArray(this.remaining() - Int.SIZE_BYTES)
    this.get(nameBytes)
    val name = String(nameBytes, Charsets.UTF_8)
    val age = this.int

    return User(id, name, age)
}

// Using Kryo

fun User.toByteBufferWithKryo(kryo: Kryo): ByteBuffer {
    val byteArrayOutputStream = ByteArrayOutputStream()
    val output = Output(byteArrayOutputStream)
    kryo.writeObject(output, this)
    output.close()

    val byteArray = byteArrayOutputStream.toByteArray()
    return ByteBuffer.wrap(byteArray)
}

fun ByteBuffer.toUserWithKryo(kryo: Kryo): User {
    val byteArray = ByteArray(this.remaining())
    this.get(byteArray)

    val byteArrayInputStream = ByteArrayInputStream(byteArray)
    val input = Input(byteArrayInputStream)
    return kryo.readObject(input, User::class.java).also { input.close() }
}