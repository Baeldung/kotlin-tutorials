package com.baeldung.serialization.jackson

import com.baeldung.serialization.Status
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object StatusSerializer : KSerializer<Status> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Status", PrimitiveKind.STRING)

    override fun serialize(
        encoder: Encoder,
        value: Status
    ) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): Status {
        return try {
            Status.valueOf(decoder.decodeString().uppercase())
        } catch (e: IllegalArgumentException) {
            Status.UNKNOWN
        }
    }
}