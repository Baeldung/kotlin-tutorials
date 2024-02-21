package com.baeldung.generic

fun <T> sort() where T : CharSequence, T : Comparable<T> {
    // sort the collection in place
}

class StringCollection<T>() where T : CharSequence, T : Comparable<T>