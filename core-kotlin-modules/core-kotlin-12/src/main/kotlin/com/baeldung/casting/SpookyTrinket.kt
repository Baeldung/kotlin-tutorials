package com.baeldung.casting

sealed class SpookyTrinket(val name: String) {
    override fun toString() = name
}
object FakeSpider : SpookyTrinket("Fake Spider")
object VampireFang : SpookyTrinket("Vampire Fang")