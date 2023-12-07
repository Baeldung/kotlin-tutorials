package com.baeldung.mapfunction

fun <T> Array<T>.mapInPlace(transform: (T) -> T): Array<T> {
    for (i in this.indices) {
        this[i] = transform(this[i])
    }
    return this
}
