package com.baeldung.sealedclasses.vs.enums

enum class OsEnum(val releaseYear: Int = 0, val company: String = "") {
    Linux(0, "Open-Source") {
        override fun getText(value: Int): String {
            return "Linux by $company - value=$value"
        }
    },
    Windows(0, "Microsoft") {
        override fun getText(value: Int): String {
            return "Windows by $company - value=$value"
        }
    },
    Mac(2001, "Apple") {
        override fun getText(value: Int): String {
            return "Mac by $company - released at $releaseYear"
        }
    },
    Unknown {
        override fun getText(value: Int): String {
            return ""
        }
    };

    abstract fun getText(value: Int): String

    fun getTextParent(): String {
        return "Called from parent enum class"
    }
}