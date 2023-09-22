package com.baeldung.serialization.kotlinx.polymorphism

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Serializable
sealed class Shape { abstract val edges: Int }

@Serializable
data class Circle(override val edges: Int, val radius: Double) : Shape()

@Serializable
@SerialName("SerialRectangle")
data class Rectangle(override val edges: Int, val width: Double, val height: Double): Shape()

class ClosedPolymorphismSerializationUnitTest {

    @Test
    fun `does not serialize concrete object's type discriminator`() {
        // given
        val concreteCircle: Circle = Circle(edges = 1, radius = 2.0)

        // when
        val serializedCircle = Json.encodeToString(concreteCircle)

        // then
        assertThat(serializedCircle).doesNotContain(""""type":"com.baeldung.serialization.kotlinx.polymorphism.Circle"""")
    }

    @Test
    fun `serializes object with it's type discriminator`() {
        // given
        val circle: Shape = Circle(edges = 1, radius = 2.0)

        // when
        val serializedCircle = Json.encodeToString(circle)
        val deserializedCircle = Json.decodeFromString<Shape>(serializedCircle)

        // then
        assertThat(serializedCircle).isEqualTo("""{"type":"com.baeldung.serialization.kotlinx.polymorphism.Circle","edges":1,"radius":2.0}""")
        assertThat(deserializedCircle).isInstanceOf(Circle::class.java)
    }

    @Test
    fun `uses custom serial name for object's type discriminator`() {
        // given
        val rectangle: Shape = Rectangle(edges = 4, width = 4.0, height = 6.0)

        // when
        val serializedRectangle = Json.encodeToString(rectangle)

        // then
        assertThat(serializedRectangle).isEqualTo("""{"type":"SerialRectangle","edges":4,"width":4.0,"height":6.0}""")
    }

    @Test
    fun `uses custom discriminator property when serializing`() {
        // given
        val circle: Shape = Circle(edges = 4, radius = 2.0)
        val jsonConfiguration = Json { classDiscriminator = "#customDiscriminatorProperty" }

        // when
        val serializedCircle = jsonConfiguration.encodeToString(circle)

        // then
        assertThat(serializedCircle).contains("""{"#customDiscriminatorProperty":"com.baeldung.serialization.kotlinx.polymorphism.Circle","edges":4,"radius":2.0}""")
    }
}