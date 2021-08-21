package com.baeldung.lateinit

class LateInit {
    private val nonNullable: Int = 12
    private val nullable: Int? = null
    private lateinit var lateinit: String

    fun isInitialized() = lateinit
}
