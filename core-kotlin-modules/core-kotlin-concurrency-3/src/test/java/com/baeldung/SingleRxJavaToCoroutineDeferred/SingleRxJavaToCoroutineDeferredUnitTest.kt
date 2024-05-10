package com.baeldung.SingleRxJavaToCoroutineDeferred

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.test.assertContains

@OptIn(DelicateCoroutinesApi::class)
class SingleRxJavaToCoroutineDeferredUnitTest {

    data class Product(val id: Int, val name: String, val price: Double)

    private fun getProducts(): Single<List<Product>> {
        return Single.just(
            listOf(
                Product(1, "Samsung", 1200.0),
                Product(2, "Oppo", 800.0),
                Product(3, "Nokia", 400.0),
                Product(4, "Lenovo", 400.0)
            )
        ).subscribeOn(Schedulers.io())
    }

    // using async
    private fun <T> Single<T>.toDeferredAsync(): Deferred<T> = runBlocking {
        return@runBlocking async { this@toDeferredAsync.blockingGet() }
    }

    @Test
    fun `test using async`(): Unit = runBlocking {
        val deferred = getProducts().toDeferredAsync().await()
        deferred.forEach { product ->
            assertThat(deferred).contains(product)
        }
        assertThat(deferred).containsExactly(
            Product(1, "Samsung", 1200.0),
            Product(2, "Oppo", 800.0),
            Product(3, "Nokia", 400.0),
            Product(4, "Lenovo", 400.0)
        )
    }

    // using GlobalScope.async
    private fun <T> Single<T>.toDeferred(): Deferred<T> = GlobalScope.async { this@toDeferred.blockingGet() }

    @Test
    fun `test using GlobalScope async`(): Unit = runBlocking {
        val deferred = getProducts().toDeferred().await()
        deferred.forEach { product ->
            assertThat(deferred).contains(product)
        }
        assertThat(deferred).containsExactly(
            Product(1, "Samsung", 1200.0),
            Product(2, "Oppo", 800.0),
            Product(3, "Nokia", 400.0),
            Product(4, "Lenovo", 400.0)
        )
    }

    // using CoroutineScope(context).async
    private fun <T> Single<T>.toDeferred(context: CoroutineContext): Deferred<T> =
        CoroutineScope(context).async { this@toDeferred.blockingGet() }

    @Test
    fun `test using CoroutineScope with context and async`(): Unit = runBlocking {
        val deferred = getProducts().toDeferred(Dispatchers.IO).await()
        deferred.forEach { product ->
            assertThat(deferred).contains(product)
        }
        assertThat(deferred).containsExactly(
            Product(1, "Samsung", 1200.0),
            Product(2, "Oppo", 800.0),
            Product(3, "Nokia", 400.0),
            Product(4, "Lenovo", 400.0)
        )
    }

    // using CompletableDeferred
    private fun <T> Single<T>.toCompletableDeferred(): CompletableDeferred<T> {
        val completableDeferred = CompletableDeferred<T>()
        this.subscribe({ completableDeferred.complete(it) }, { completableDeferred.completeExceptionally(it) })
        return completableDeferred
    }

    @Test
    fun `test using CompletableDeferred`(): Unit = runBlocking {
        val deferred = getProducts().toCompletableDeferred().await()
        deferred.forEach { product ->
            assertThat(deferred).contains(product)
        }
        assertThat(deferred).containsExactly(
            Product(1, "Samsung", 1200.0),
            Product(2, "Oppo", 800.0),
            Product(3, "Nokia", 400.0),
            Product(4, "Lenovo", 400.0)
        )
    }

    // using suspend
    private suspend fun <T> Single<T>.toDeferredWithSuspend(): T = suspendCoroutine { continuation ->
        this.subscribe({ continuation.resume(it) }, { continuation.resumeWithException(it) })
    }

    @Test
    fun `test using suspend`(): Unit = runBlocking {
        val deferred = getProducts().toDeferredWithSuspend()
        deferred.forEach { product ->
            assertThat(deferred).contains(product)
        }
        assertThat(deferred).containsExactly(
            Product(1, "Samsung", 1200.0),
            Product(2, "Oppo", 800.0),
            Product(3, "Nokia", 400.0),
            Product(4, "Lenovo", 400.0)
        )
    }

}