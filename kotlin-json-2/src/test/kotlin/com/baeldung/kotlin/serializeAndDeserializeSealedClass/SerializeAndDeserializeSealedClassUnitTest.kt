package com.baeldung.serializeAndDeserializeSealedClass

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@Serializable
sealed class Animal {
    abstract val name: String

    @Serializable
    data class Dog(override val name: String, val breed: String) : Animal()

    @Serializable
    data class Cat(override val name: String, val color: String) : Animal()
}

class SerializeAndDeserializeSealedClassUnitTest {

    val moshi: Moshi = Moshi.Builder()
        .add(
            PolymorphicJsonAdapterFactory.of(Animal::class.java, "type")
                .withSubtype(Animal.Dog::class.java, "dog")
                .withSubtype(Animal.Cat::class.java, "cat")
        )
        .add(KotlinJsonAdapterFactory())
        .build()

    @Test
    fun `serialize sealed class using kotlinx serialization library`() {
        val dog = Animal.Dog("Buddy", "Labrador")
        val serializedDog = Json.encodeToString(dog)

        val cat = Animal.Cat("Mike", "Black")
        val serializedCat = Json.encodeToString(cat)

        assertEquals("{\"name\":\"Buddy\",\"breed\":\"Labrador\"}", serializedDog)
        assertEquals("{\"name\":\"Mike\",\"color\":\"Black\"}", serializedCat)
    }

    @Test
    fun `deserialize sealed class using kotlinx serialization library`() {
        val json = Json {
            serializersModule = SerializersModule {
                polymorphic(Animal::class) {
                    subclass(Animal.Dog::class, Animal.Dog.serializer())
                    subclass(Animal.Cat::class, Animal.Cat.serializer())
                }
            }
        }
        val dog = Animal.Dog("Buddy", "Labrador")
        val serializedDog = json.encodeToString(Animal.serializer(), dog)
        val deserializedDog = json.decodeFromString<Animal>(serializedDog)

        assertEquals(dog, deserializedDog)

        val cat = Animal.Cat("Mike", "Black")
        val serializedCat = json.encodeToString(Animal.serializer(), cat)
        val deserializedCat = json.decodeFromString<Animal>(serializedCat)

        assertEquals(cat, deserializedCat)
    }

    @Test
    fun `serialize sealed class using moshi library`() {

        val dog = Animal.Dog("Buddy", "Labrador")
        val cat = Animal.Cat("Mike", "Black")

        val jsonAdapter = moshi.adapter(Animal::class.java)

        val serializedDog = jsonAdapter.toJson(dog)
        val serializedCat = jsonAdapter.toJson(cat)

        assertEquals("{\"type\":\"dog\",\"name\":\"Buddy\",\"breed\":\"Labrador\"}", serializedDog)
        assertEquals("{\"type\":\"cat\",\"name\":\"Mike\",\"color\":\"Black\"}", serializedCat)
    }

    @Test
    fun `deserialize sealed class using moshi library`() {
        val jsonAdapter = moshi.adapter(Animal::class.java)

        val serializedDog = """{"type":"dog","name":"Buddy","breed":"Labrador"}"""
        val deserializedDog = jsonAdapter.fromJson(serializedDog) as Animal.Dog

        val serializedCat = """{"type":"cat","name":"Mike","color":"Black"}"""
        val deserializedCat = jsonAdapter.fromJson(serializedCat) as Animal.Cat

        assertEquals(Animal.Dog("Buddy", "Labrador"), deserializedDog)
        assertEquals(Animal.Cat("Mike", "Black"), deserializedCat)
    }

}