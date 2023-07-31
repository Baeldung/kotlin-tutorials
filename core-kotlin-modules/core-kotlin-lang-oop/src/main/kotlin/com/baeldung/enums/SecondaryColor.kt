package com.baeldung.enums

enum class SecondaryColor : ISecondaryColor {
    GREEN {
        override fun paint(): String {
            return "green"
        }
    };
}