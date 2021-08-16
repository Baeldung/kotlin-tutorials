package com.baeldung.kotlin.enums.vs.sealed.classes

sealed class OsSealed(val releaseYear: Int = 0, val company: String = "") {
    constructor(company: String) : this(0, company)

    object Linux : OsSealed("Open-Source") {
        fun getText(value: Int): String {
            return "Linux by $company - value=$value"
        }
    }

    object Windows : OsSealed("Microsoft") {
        fun getNumber(value: String): Int {
            return value.length
        }
    }

    object Mac : OsSealed(2001, "Apple") {
        fun doSomething(): String {
            val s = "Mac by $company - released at $releaseYear"
            println(s)
            return s
        }
    }

    object Unknown : OsSealed()

    fun printParent() {
        println("Called from parent sealed class")
    }
}