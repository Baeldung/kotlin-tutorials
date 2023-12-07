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

fun basicTryCatch() {
        try {
            // Code that might throw an exception
        } catch (e: Throwable) {
            // Handle exception here
            val stackTrace = e.stackTrace
            // do something with the stackTrace
        }

        val result: Result<*> = runCatching {
            // Code that might throw an exception
        }

        result.fold(
            onSuccess = { value ->
                // Handle the successful execution
            },
            onFailure = { exception ->
                // Handle exception here
            }
        )
    }