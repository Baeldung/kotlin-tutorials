package com.baeldung.singleRxJavaToCoroutineDeferred

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.rx3.await
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.test.assertTrue

@OptIn(DelicateCoroutinesApi::class)
class SingleRxJavaToCoroutineDeferredUnitTest {

    data class Product(val id: Int, val name: String, val price: Double)

    private val allProducts = listOf(
        Product(1, "Samsung", 1200.0),
        Product(2, "Oppo", 800.0),
        Product(3, "Nokia", 450.0),
        Product(4, "Lenovo", 550.0),
        Product(5, "ASUS", 400.0)
    )

    private fun getFilteredProducts(): Single<List<Product>> {
        return Single.just(
            allProducts
        ).map { products ->
            products.sortedBy { it.price }.filter { it.price > 500 }
        }.subscribeOn(Schedulers.io())
    }

    private suspend fun Deferred<*>.assertResultsTrue() {
        assertTrue(actual = this is Deferred<*>)

        assertThat(this.await() as List<*>).containsExactly(
            Product(4, "Lenovo", 550.0),
            Product(2, "Oppo", 800.0),
            Product(1, "Samsung", 1200.0)
        )
    }

    // using async directly
    @Test
    fun `using async direcly`() = runBlocking {
        val deferred = async {
            getFilteredProducts().blockingGet()
        }
        deferred.assertResultsTrue()
    }

    // using async with extension
    private fun <T : Any> Single<T>.toDeferredAsync(): Deferred<T> =
        runBlocking { async { this@toDeferredAsync.blockingGet() } }


    @Test
    fun `test using async with extension`() = runBlocking {
        val deferredExt = getFilteredProducts().toDeferredAsync()
        deferredExt.assertResultsTrue()
    }

    // using GlobalScope.async
    private fun <T : Any> Single<T>.toDeferredGlobalAsync(): Deferred<T> =
        GlobalScope.async { this@toDeferredGlobalAsync.blockingGet() }

    @Test
    fun `test using GlobalScope async`() = runBlocking {
        val deferred = getFilteredProducts().toDeferredGlobalAsync()
        deferred.assertResultsTrue()
    }

    // using CoroutineScope(context).async
    private fun <T : Any> Single<T>.toDeferredWithContext(context: CoroutineContext): Deferred<T> =
        CoroutineScope(context).async { this@toDeferredWithContext.blockingGet() }

    @Test
    fun `test using CoroutineScope with context and async`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredWithContext(Dispatchers.IO)
        deferred.assertResultsTrue()
    }

    // using CompletableDeferred
    private fun <T : Any> Single<T>.toCompletableDeferred(): CompletableDeferred<T> {
        val completableDeferred = CompletableDeferred<T>()
        this.subscribe({ completableDeferred.complete(it) }, { completableDeferred.completeExceptionally(it) })
        return completableDeferred
    }

    // using CompletableDeferred with callback
    private fun <T : Any> Single<T>.toCompletableDeferred(
        onSuccess: (CompletableDeferred<T>, T) -> Unit,
        onError: (CompletableDeferred<T>, Throwable) -> Unit
    ): CompletableDeferred<T> {
        val completableDeferred = CompletableDeferred<T>()
        this.subscribe({ result ->
            completableDeferred.complete(result)
            onSuccess(completableDeferred, result)
        }, { error ->
            completableDeferred.completeExceptionally(error)
            onError(completableDeferred, error)
        })
        return completableDeferred
    }


    @Test
    fun `test using CompletableDeferred`() = runBlocking {
        val deferred = getFilteredProducts().toCompletableDeferred()
        deferred.assertResultsTrue()
    }

    @Test
    fun `test using CompletableDeferred with callback`(): Unit = runBlocking {
        getFilteredProducts().toCompletableDeferred(
            onSuccess = { deferredResult, _ ->
                runBlocking { deferredResult.assertResultsTrue() }
            },
            onError = { _, error ->
                println("Error: ${error.message}")
            }
        ).await()
    }

    // using suspendCoroutines directly
    @Test
    fun `using suspendCoroutines directly`(): Unit = runBlocking{
        val deffered = async {
            suspendCoroutine { continuation ->
                getFilteredProducts().subscribe { result ->
                    continuation.resume(result)
                }
            }
        }
        deffered.assertResultsTrue()
    }

    // using suspendCoroutines with extension
    private fun <T : Any> Single<T>.toDeferredWithSuspend(): Deferred<T> {
        return GlobalScope.async {
            suspendCoroutine { continuation ->
                this@toDeferredWithSuspend.subscribe { result ->
                    continuation.resume(result)
                }
            }
        }
    }

    @Test
    fun `test using suspendCoroutine`() = runBlocking {
        val deferred = getFilteredProducts().toDeferredWithSuspend()
        deferred.assertResultsTrue()
    }

    // using suspendCancellableCoroutine
    private fun <T : Any> Single<T>.toDeferredWithsuspendCancellableCoroutine(
        onSuccess: (Deferred<T>) -> Unit, onError: (Throwable) -> Unit
    ): Deferred<T> {
        return GlobalScope.async {
            suspendCancellableCoroutine { continuation ->
                this@toDeferredWithsuspendCancellableCoroutine.subscribe({ result ->
                    val deferredResult = CompletableDeferred<T>().apply {
                        complete(result)
                        continuation.resume(result)
                    }
                    onSuccess(deferredResult)
                }, { error ->
                    continuation.resumeWithException(error)
                    onError(error)
                })
            }
        }
    }


    @Test
    fun `test using suspendCancellableCoroutine with custom callback`(): Unit = runBlocking {
        getFilteredProducts().toDeferredWithsuspendCancellableCoroutine(
            onSuccess = { deferredResult ->
                runBlocking { deferredResult.assertResultsTrue() }
            },
            onError = { error ->
                println("Error: ${error.message}")
            }
        ).await()
    }

    // using rx3 directly
    @Test
    fun `using rx3 directly`() = runBlocking{
        val deferred = async {
            getFilteredProducts().await()
        }
        deferred.assertResultsTrue()
    }

    // using rx3 ext
    private suspend fun <T : Any> Single<T>.toDeferredRx3(): Deferred<T> =
        CoroutineScope(Dispatchers.IO).async { this@toDeferredRx3.await() }

    @Test
    fun `test using rx3 ext`() = runBlocking {
        val deferred = getFilteredProducts().toDeferredRx3()
        deferred.assertResultsTrue()
    }

    // using rx3 with context
    private fun <T : Any> Single<T>.toDeferredRx3WithContext(context: CoroutineContext): Deferred<T> =
        CoroutineScope(context).async { this@toDeferredRx3WithContext.await() }

    @Test
    fun `test using rx3 with context`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredRx3WithContext(Dispatchers.IO)
        deferred.assertResultsTrue()
    }

}