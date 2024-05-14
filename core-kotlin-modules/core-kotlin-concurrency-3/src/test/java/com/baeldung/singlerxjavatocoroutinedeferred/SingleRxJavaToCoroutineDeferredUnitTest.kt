package com.baeldung.singlerxjavatocoroutinedeferred

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.rx3.await
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.test.assertTrue


class SingleRxJavaToCoroutineDeferredUnitTest {

    private val logger = LoggerFactory.getLogger("")

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

    private suspend fun Deferred<*>.assertOver500AndSorted() {
        assertTrue(actual = this is Deferred<*>)

        assertThat(this.await() as List<*>).containsExactly(
            Product(4, "Lenovo", 550.0),
            Product(2, "Oppo", 800.0),
            Product(1, "Samsung", 1200.0)
        )
    }

    @Test
    fun `using async direcly and blockingGet`() = runBlocking {
        val deferred = async { getFilteredProducts().blockingGet() } // simple, but must be careful because blocking main thread
        deferred.assertOver500AndSorted() // assertion test
    }

    // using async with extension
    private suspend fun <T : Any> Single<T>.toDeferredAsync(): Deferred<T> =
        coroutineScope { async { this@toDeferredAsync.blockingGet() } }


    @Test
    fun `test using async with extension`() = runBlocking {
        val deferredExt = getFilteredProducts().toDeferredAsync()
        deferredExt.assertOver500AndSorted()
    }

    // using CoroutineScope(context).async
    private fun <T : Any> Single<T>.toDeferredWithContext(context: CoroutineContext): Deferred<T> =
        CoroutineScope(context).async { this@toDeferredWithContext.blockingGet() }

    @Test
    fun `test using CoroutineScope with context and async`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredWithContext(Dispatchers.IO)
        deferred.assertOver500AndSorted()
    }

    // using CompletableDeferred & subscribe
    @Test
    fun `using CompletableDeferred and subscribe`() = runBlocking {
        val deferred = CompletableDeferred<List<Product>>()
        getFilteredProducts().subscribe({ products ->
            deferred.complete(products)
        }, { error ->
            deferred.completeExceptionally(error)
        })
        deferred.assertOver500AndSorted()
    }

    // using CompletableDeferred extension
    private fun <T : Any> Single<T>.toCompletableDeferred(): CompletableDeferred<T> {
        val completableDeferred = CompletableDeferred<T>()
        this.subscribe({ completableDeferred.complete(it) }, { completableDeferred.completeExceptionally(it) })
        return completableDeferred
    }

    // using CompletableDeferred with custom callback
    private fun <T : Any> Single<T>.toCompletableDeferred(
        onSuccess: (CompletableDeferred<T>, T) -> Unit, onError: (CompletableDeferred<T>, Throwable) -> Unit
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
    fun `test using CompletableDeferred extension`() = runBlocking {
        val deferred = getFilteredProducts().toCompletableDeferred()
        deferred.assertOver500AndSorted()
    }

    @Test
    fun `test using CompletableDeferred with callback`(): Unit = runBlocking {
        getFilteredProducts().toCompletableDeferred(onSuccess = { deferredResult, _ ->
            launch { deferredResult.assertOver500AndSorted() }
        }, onError = { _, error ->
            logger.debug("Error: ${error.message}")
        }).await()
    }

    // using suspendCoroutines directly
    @Test
    fun `using suspendCoroutines directly`(): Unit = runBlocking {
        val defered = async {
            suspendCoroutine { continuation ->
                getFilteredProducts().subscribe { result ->
                    continuation.resume(result)
                }
            }
        }
        defered.assertOver500AndSorted()
    }

    // using suspendCoroutines with extension
    private suspend fun <T : Any> Single<T>.toDeferredWithSuspend(): Deferred<T> {
        return coroutineScope {
            async {
                suspendCoroutine { continuation ->
                    this@toDeferredWithSuspend.subscribe { result ->
                        continuation.resume(result)
                    }
                }
            }
        }
    }

    @Test
    fun `test using suspendCoroutine extension`() = runBlocking {
        val deferred = getFilteredProducts().toDeferredWithSuspend()
        deferred.assertOver500AndSorted()
    }

    // using suspendCancellableCoroutine
    private suspend fun <T : Any> Single<T>.toDeferredWithSuspendCancellableCoroutine(
        onSuccess: (Deferred<T>) -> Unit, onError: (Throwable) -> Unit
    ): Deferred<T> {
        return coroutineScope {
            async {
                suspendCancellableCoroutine { continuation ->
                    this@toDeferredWithSuspendCancellableCoroutine.subscribe({ result ->
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
    }
    @Test
    fun `test using suspendCancellableCoroutine with custom callback`(): Unit = runBlocking {
        getFilteredProducts().toDeferredWithSuspendCancellableCoroutine(onSuccess = { deferredResult ->
            launch { deferredResult.assertOver500AndSorted() }
        }, onError = { error ->
            logger.debug("Error: ${error.message}")
        }).await()
    }

    // using rx3 directly
    @Test
    fun `using rx3 directly`() = runBlocking {
        val deferred = async { getFilteredProducts().await() }
        deferred.assertOver500AndSorted()
    }

    // using rx3 ext
    private suspend fun <T : Any> Single<T>.toDeferredRx3(): Deferred<T> =
        coroutineScope { async { this@toDeferredRx3.await() } }

    @Test
    fun `test using rx3 ext`() = runBlocking {
        val deferred = getFilteredProducts().toDeferredRx3()
        deferred.assertOver500AndSorted()
    }

    // using rx3 with context
    private fun <T : Any> Single<T>.toDeferredRx3WithContext(context: CoroutineContext): Deferred<T> =
        CoroutineScope(context).async { this@toDeferredRx3WithContext.await() }

    @Test
    fun `test using rx3 with context`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredRx3WithContext(Dispatchers.IO)
        deferred.assertOver500AndSorted()
    }

}