package com.baeldung.exception

fun getLineNumber(exception: Exception): Int? {
    val stackTraceElement = exception.stackTrace.firstOrNull()
    return stackTraceElement?.lineNumber
}

fun getFirstStacktraceElement(exception: Exception): String? {
    return exception.stackTrace.firstOrNull()?.className
}

fun getFileName(exception: Exception): String? {
    return exception.stackTrace.firstOrNull()?.fileName
}


fun myFunction() {
    try {
        // Code that might throw an exception
    } catch (e: Exception) {
        val lineNumber = getLineNumber(e)
        val firstStackClassName = getFirstStacktraceElement(e)
        val fileName = getFileName(e)

        println("Exception occurred at $fileName.$firstStackClassName L#$lineNumber")
    }

}