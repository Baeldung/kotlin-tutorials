package com.baeldung.functionalErrorHandling

import java.util.concurrent.CancellationException


object Nullable {
    inline fun <T> nullable(f: () -> T): T? = try {
        f()
    } catch (e: CancellationException) {
        throw e
    } catch (e: Error) {
        throw e
    } catch (e: Throwable) {
        null
    }
}
