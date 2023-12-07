package com.baeldung.functionalErrorHandling
import java.util.concurrent.CancellationException
import kotlin.Result.Companion.failure

object ResultMonad {

    inline fun <T> Result.Companion.catch(f: () -> T): Result<T> {
        return try {
            success(f())
        } catch (e: CancellationException) {
            throw e
        } catch (e: Error) {
            throw e
        } catch (e: Throwable) {
            failure(e)
        }
    }

    inline fun <A, B> Result<A>.flatMap(f: (A) -> Result<B>): Result<B> = when (isFailure) {
        true -> failure(exceptionOrNull()!!)
        false -> f(getOrNull()!!)
    }
}


