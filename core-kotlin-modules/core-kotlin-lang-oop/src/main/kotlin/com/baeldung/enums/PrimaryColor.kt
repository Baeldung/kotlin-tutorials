package com.baeldung.enums

enum class PrimaryColor : IPrimaryColor {
    RED {
        override fun paint(): String {
            return "red"
        }
    };
}