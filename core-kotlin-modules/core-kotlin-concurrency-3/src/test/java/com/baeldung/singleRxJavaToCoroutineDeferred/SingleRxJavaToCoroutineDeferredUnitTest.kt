package com.baeldung.singleRxJavaToCoroutineDeferred

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.rx2.await
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

    private fun List<Product>.assertResultsTrue() {
        assertThat(this).containsExactly(
            Product(4, "Lenovo", 550.0), Product(2, "Oppo", 800.0), Product(1, "Samsung", 1200.0)
        )
    }

    // using async
    private fun <T> Single<T>.toDeferredAsync(): Deferred<T> =
        runBlocking { async { this@toDeferredAsync.blockingGet() } }


    @Test
    fun `test using async`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredAsync().await()
        deferred.forEach {
            assertThat(deferred).contains(it)
        }
        deferred.assertResultsTrue()
    }

    // using GlobalScope.async
    private fun <T> Single<T>.toDeferredGlobalAsync(): Deferred<T> =
        GlobalScope.async { this@toDeferredGlobalAsync.blockingGet() }

    @Test
    fun `test using GlobalScope async`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredGlobalAsync().await()
        deferred.forEach {
            assertThat(deferred).contains(it)
        }
        deferred.assertResultsTrue()
    }

    // using CoroutineScope(context).async
    private fun <T> Single<T>.toDeferredWithContext(context: CoroutineContext): Deferred<T> =
        CoroutineScope(context).async { this@toDeferredWithContext.blockingGet() }

    @Test
    fun `test using CoroutineScope with context and async`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredWithContext(Dispatchers.IO).await()
        deferred.forEach {
            assertThat(deferred).contains(it)
        }
        deferred.assertResultsTrue()
    }

    // using CompletableDeferred
    private fun <T> Single<T>.toCompletableDeferred(): CompletableDeferred<T> {
        val completableDeferred = CompletableDeferred<T>()
        this.subscribe({ completableDeferred.complete(it) }, { completableDeferred.completeExceptionally(it) })
        return completableDeferred
    }

    @Test
    fun `test using CompletableDeferred`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toCompletableDeferred().await()
        deferred.forEach {
            assertThat(deferred).contains(it)
        }
        deferred.assertResultsTrue()
    }

    // using suspendCoroutine
    private suspend fun <T> Single<T>.toDeferredWithSuspend(): T = suspendCoroutine { continuation ->
        this.subscribe({ continuation.resume(it) }, { continuation.resumeWithException(it) })
    }

    @Test
    fun `test using suspendCoroutine`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredWithSuspend()
        deferred.forEach {
            assertThat(deferred).contains(it)
        }
        deferred.assertResultsTrue()
    }

    // using rx2
    private suspend fun <T> Single<T>.toDeferredRx2(): T = this.await()

    @Test
    fun `test using rx2`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredRx2()
        deferred.forEach {
            assertThat(deferred).contains(it)
        }
        deferred.assertResultsTrue()
    }

    // using rx2 with context
    private fun <T> Single<T>.toDeferredRx2WithContext(context: CoroutineContext): Deferred<T> =
        CoroutineScope(context).async { this@toDeferredRx2WithContext.await() }

    @Test
    fun `test using rx2 with context`(): Unit = runBlocking {
        val deferred = getFilteredProducts().toDeferredRx2WithContext(Dispatchers.IO).await()
        deferred.forEach {
            assertThat(deferred).contains(it)
        }
        deferred.assertResultsTrue()
    }

}