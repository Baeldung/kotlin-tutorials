package com.baeldung.kotlin.multiplatform

internal actual fun writeLogMessage(message: String, logLevel: LogLevel) {
    when (logLevel) {
        LogLevel.DEBUG -> console.log("Running in JS: $message")
        LogLevel.WARN -> console.warn("Running in JS: $message")
        LogLevel.ERROR -> console.error("Running in JS: $message")
    }
}