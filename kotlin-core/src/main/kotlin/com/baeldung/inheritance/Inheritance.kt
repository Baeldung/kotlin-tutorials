package com.baeldung.inheritance

open class Try {
    open val value: Any? = null
    open fun isSuccess(): Boolean = false
}
open class Success(override val value: Any?) : Try() {
    final override fun isSuccess(): Boolean = true
}

class Sealed