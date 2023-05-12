package com.baeldung.generic.inline

@JvmInline
value class Wrapper<T>(val value: T)

fun main() {
    val wrapper = Wrapper<Int>(42)
    val value: Int = wrapper.value // No runtime overhead
    val list = listOf(42)
    val item: Int = list[0] // Boxing and unboxing overhead
}

@JvmInline
value class Id<T>(val value: T)

interface Identifiable<T> {
    val id: Id<T>
}

data class User(override val id: Id<String>, val name: String) : Identifiable<String>

data class Wallet(override val id: Id<Int>, val currency: String) : Identifiable<Int>

fun <ID, T : Identifiable<out ID>> findById(entities: List<T>, id: Id<ID>): T? {
    return entities.firstOrNull { it.id == id }
}


@JvmInline
value class Percentage(val value: Double) {
    init {
        require(value in 0.0..100.0) { "Percentage value must be between 0 and 100" }
    }
}

@JvmInline
value class Box<T>(val value: T)

val intBox: Box<Int> = Box(42)
val stringBox: Box<String> = Box("Hello, Baeldung!")


@JvmInline
value class Name<T : CharSequence>(val value: T) {
    operator fun plus(other: Name<T>): Name<CharSequence> = Name("$value ${other.value}")
}
