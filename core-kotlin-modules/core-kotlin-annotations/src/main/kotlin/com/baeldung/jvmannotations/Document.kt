package com.baeldung.jvmannotations


interface Document {

    // @JvmDefault deprecation level ERROR. Switched to new -Xjvm-default modes: all or all-compatibility
    fun getTypeDefault() = "document"

    fun getType() = "document"
}
