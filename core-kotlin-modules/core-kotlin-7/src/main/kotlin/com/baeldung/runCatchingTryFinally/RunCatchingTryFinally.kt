package com.baeldung.runCatchingTryFinally

fun tryToDoSomething(): Int {
    var result: Int
    val resource = acquireResource()
    try {
        // Code that can throw an exception
        result = performAction(resource)
    } finally {
        releaseResource(resource)
    }
    return result
}

fun doSomethingRunCatching(): Int {
    val resource = acquireResource()
    val result = runCatching {
        // Code that can throw an exception
        performAction(resource)
    }
    releaseResource(resource)
    return result.getOrThrow()
}


fun acquireResource() = Any()
fun releaseResource(any: Any) {
    println("Any is: $any")
}
fun performAction(any: Any): Int {
    println("Any is: $any")
    return 1
}
