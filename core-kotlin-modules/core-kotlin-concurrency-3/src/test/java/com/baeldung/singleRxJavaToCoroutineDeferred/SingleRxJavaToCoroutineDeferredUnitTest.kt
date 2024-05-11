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
import kotlin.test.assertTrue

/*
    RxJava (Single)                             Kotlin Coroutines (Deferred)
    -------------                               ------------------------
    Observable                                  CoroutineScope
        |                                           |
        v                                           v
    Single                                      Deferred
        |                                           |
        v                                           v
    (subscribeOn)                               (await)
*/

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

    private suspend fun Deferred<*>.assertResultsTrue(){

        assertTrue(actual = this is kotlinx.coroutines.Deferred<*>)

        assertThat(this.await() as List<*>).containsExactly(
            Product(4, "Lenovo", 550.0),
            Product(2, "Oppo", 800.0),
            Product(1, "Samsung", 1200.0)
        )
    }

    // using async
    private fun <T : Any> Single<T>.toDeferredAsync(): Deferred<T> =
        runBlocking { async { this@toDeferredAsync.blockingGet() } }


    @Test
    fun `test using async`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredAsync()
        deferred.assertResultsTrue()
    }

    // using GlobalScope.async
    private fun <T : Any> Single<T>.toDeferredGlobalAsync(): Deferred<T> =
        GlobalScope.async { this@toDeferredGlobalAsync.blockingGet() }

    @Test
    fun `test using GlobalScope async`(): Unit = runBlocking {
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

    @Test
    fun `test using CompletableDeferred`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toCompletableDeferred()
        deferred.assertResultsTrue()
    }

    // using suspendCoroutine
    private fun <T : Any> Single<T>.toDeferredWithSuspend(): Deferred<T> {
        return GlobalScope.async {
            suspendCancellableCoroutine { continuation ->
                this@toDeferredWithSuspend.subscribe({ result ->
                    continuation.resume(result)
                }, { error ->
                    continuation.resumeWithException(error)
                })
            }
        }
    }

    @Test
    fun `test using suspendCoroutine`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredWithSuspend()
        deferred.assertResultsTrue()
    }

    // using suspendCoroutine with custom scope
    private suspend fun <T : Any> Single<T>.toDeferredWithSuspend(scope: CoroutineScope): Deferred<T> {
        return scope.async {
            suspendCancellableCoroutine { continuation ->
                this@toDeferredWithSuspend.subscribe({ result ->
                    continuation.resume(result)
                }, { error ->
                    continuation.resumeWithException(error)
                })
            }
        }
    }

    @Test
    fun `test using suspendCoroutine with custom Scope`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredWithSuspend(this)
        deferred.assertResultsTrue()
    }

    // using rx3
    private suspend fun <T : Any> Single<T>.toDeferredRx2(): Deferred<T> =
        CoroutineScope(Dispatchers.IO).async { this@toDeferredRx2.await() }

    @Test
    fun `test using rx3`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredRx2()
        deferred.assertResultsTrue()
    }

    // using rx3 with context
    private fun <T : Any> Single<T>.toDeferredRx2WithContext(context: CoroutineContext): Deferred<T> =
        CoroutineScope(context).async { this@toDeferredRx2WithContext.await() }

    @Test
    fun `test using rx3 with context`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredRx2WithContext(Dispatchers.IO)
        deferred.assertResultsTrue()
    }

}