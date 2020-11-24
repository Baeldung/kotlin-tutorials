package com.baeldung.returnlabel

fun <T> List<T>.findOne(x: T): Int {
    forEachIndexed { i, v ->
        if (v == x) {
            return i
        }
    }

    return -1;
}

fun <T> List<T>.printIndexOf(x: T) {
    forEachIndexed { i, v ->
        if (v == x) {
            print("Found $v at $i")
            return@forEachIndexed
        }
    }
}

fun <T> List<T>.printIndexOf2(x: T) {
    forEachIndexed loop@{ i, v ->
        if (v == x) {
            print("Found $v at $i")
            return@loop
        }
    }
}

fun <T> List<List<T>>.nestedFind(x: T) {
    forEach {
        it.forEachIndexed { i2, v2 ->
            if (v2 == x) {
                println("Found $v2 at $i2")
                return@forEach
            }
        }
    }
}

fun <T> List<T>.printIndexOfAnonymous(x: T) {
    forEachIndexed(fun(i: Int, v: T) {
        if (v == x) {
            print("Found $v at $i")
            return
        }
    })
}
