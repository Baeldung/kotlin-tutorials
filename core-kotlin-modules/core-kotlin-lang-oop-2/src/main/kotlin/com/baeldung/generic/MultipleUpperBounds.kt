package com.baeldung.generic

fun <T> sort(xs: List<T>) where T : CharSequence, T : Comparable<T> {
    // sort the collection in place
}

class StringCollection<T>(xs: List<T>) where T : CharSequence, T : Comparable<T>