package com.baeldung.runcatching_tryfinally

fun tryToDoSomething(): Int {
    var result = 0
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
fun releaseResource(any: Any) = Any()
fun performAction(any: Any) = 1
