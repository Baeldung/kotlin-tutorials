package com.example.simplekmmapplication

import platform.Foundation.*

actual class DateTimeApi actual constructor() {
    actual fun getCurrentTimestamp(): Long {
        return NSDate.date().timeIntervalSince1970().toLong()
    }
}