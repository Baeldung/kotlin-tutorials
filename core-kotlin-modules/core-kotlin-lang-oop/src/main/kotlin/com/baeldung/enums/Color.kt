package com.baeldung.enums

sealed class Color {
    object RED : Color() {
        override fun paint(): String {
            return "red"
        }

        override fun type(): ColorType {
            return ColorType.PRIMARY
        }
    }

    object GREEN : Color() {
        override fun paint(): String {
            return "green"
        }

        override fun type(): ColorType {
            return ColorType.SECONDARY
        }
    }

    abstract fun type(): ColorType

    abstract fun paint(): String
}
