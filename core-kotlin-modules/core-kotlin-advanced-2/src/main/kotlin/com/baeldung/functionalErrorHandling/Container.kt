package com.baeldung.functionalErrorHandling

import java.util.concurrent.CancellationException

sealed class Container<out L, out R> {

    data class Failure<L>(val value: L) : Container<L, Nothing>()

    data class Success<R>(val value: R) : Container<Nothing, R>()

    companion object {
        inline fun <R> catch(block: () -> R): Container<Throwable, R> {
            return try {
                Success(block())
            } catch (e: CancellationException) {
                throw e
            } catch (e: Error) {
                throw e
            } catch (e: Throwable) {
                Failure(e)
            }
        }
    }
}
