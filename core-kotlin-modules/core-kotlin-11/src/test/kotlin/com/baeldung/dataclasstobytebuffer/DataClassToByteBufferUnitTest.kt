package com.baeldung.dataclasstobytebuffer

import com.esotericsoftware.kryo.Kryo
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class DataClassToByteBufferUnitTest {

    private val kryo = Kryo().apply {
        register(User::class.java)
    }

    @Test
    fun `Manual serialize and deserialize with fixed size`() {
        val user = User(id = 1, name = "Alice", age = 30)

        val serializedUser = user.manualToByteBuffer()

        val deserializedUser = serializedUser.manualToUser()

        assertEquals(user, deserializedUser)
    }

    @Test
    fun `Serialize and deserialize using byte array output and input stream`() {
        val user = UserInputOutputStream(id = 1, name = "Alice", age = 30)

        val serializedUser = user.outputStreamByteBuffer()

        val deserializedUser = serializedUser.inputStreamToUser()

        assertEquals(user, deserializedUser)
    }

    @Test
    fun `Serialize and deserialize using kotlin serialization library`() {
        val user = UserSerialization(id = 1, name = "Alice", age = 30)

        val serializedUser = user.userToJson()

        val deserializedUser = serializedUser.jsonToUser()

        assertEquals(user, deserializedUser)
    }

    @Test
    fun `Serialize and deserialize using Kryo library`() {
        val user = User(id = 1, name = "Alice", age = 30)

        val serializedUser = user.toByteBufferWithKryo(kryo)

        val deserializedUser = serializedUser.toUserWithKryo(kryo)

        assertEquals(user, deserializedUser)
    }
}