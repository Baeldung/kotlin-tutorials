package com.example.simplekmmapplication

import java.time.OffsetDateTime

actual class DateTimeApi actual constructor() {
    actual fun getCurrentTimestamp() : Long {
        return OffsetDateTime.now().toEpochSecond()
    }
}