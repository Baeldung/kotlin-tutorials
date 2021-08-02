package com.baeldung.throwsannotation

import java.io.IOException

fun throwJavaUnchecked() {
    throw IllegalArgumentException()
}

@Throws(IOException::class)
fun throwJavaChecked() {
    throw IOException()
}
