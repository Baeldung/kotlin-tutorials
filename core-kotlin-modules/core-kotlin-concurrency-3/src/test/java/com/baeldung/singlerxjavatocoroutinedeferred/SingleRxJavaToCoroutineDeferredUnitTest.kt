package com.baeldung.singlerxjavatocoroutinedeferred

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.rx3.await
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

    private suspend fun Deferred<*>.assertOver500AndSorted() {
        assertThat(this.await() as List<*>).containsExactly(
            Product(4, "Lenovo", 550.0),
            Product(2, "Oppo", 800.0),
            Product(1, "Samsung", 1200.0)
        )
    }

    @Test
    fun `using async and blockingGet`() = runBlocking {
        val deferred =
            async { getFilteredProducts().blockingGet() } // potentially blocking main thread while not careful
        deferred.assertOver500AndSorted() // assertion test
    }

    @Test
    fun `using subscribe and CompletableDeferred`() = runBlocking {
        val deferred = CompletableDeferred<List<Product>>()
        getFilteredProducts().subscribe({ products ->
            deferred.complete(products)
        }, { error ->
            deferred.completeExceptionally(error)
        })
        deferred.assertOver500AndSorted()
    }

    @Test
    fun `using suspendCoroutines`(): Unit = runBlocking {
        val deferred = async {
            suspendCoroutine { continuation ->
                getFilteredProducts().subscribe { result ->
                    continuation.resume(result)
                }
            }
        }
        deferred.assertOver500AndSorted()
    }

    @Test
    fun `using suspendCancellableCoroutine`(): Unit = runBlocking {
        val deferred = async {
            suspendCancellableCoroutine { continuation ->
                getFilteredProducts().subscribe({ result ->
                    continuation.resume(result)
                }, { error ->
                    continuation.resumeWithException(error)
                })
            }
        }
        deferred.assertOver500AndSorted()
    }

    @Test
    fun `using Kotlin Coroutines Rx3`() = runBlocking {
        val deferred = async { getFilteredProducts().await() }
        deferred.assertOver500AndSorted()
    }

}