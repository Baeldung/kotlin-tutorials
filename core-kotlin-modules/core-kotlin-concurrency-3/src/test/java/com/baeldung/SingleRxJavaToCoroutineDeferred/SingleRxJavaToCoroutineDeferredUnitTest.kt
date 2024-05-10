package com.baeldung.SingleRxJavaToCoroutineDeferred

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.rx2.await
import java.util.*

class SingleRxJavaToCoroutineDeferredUnitTest {

    data class Product(val id: Int, val name: String, val price: Double)

    fun getProducts(): Single<List<Product>> {
        return Single.just(
            listOf(
                Product(1, "Samsung", 1200.0),
                Product(2, "Oppo", 800.0),
                Product(3, "Nokia", 400.0),
                Product(4, "Lenovo", 400.0)
            )
        ).subscribeOn(Schedulers.io())
    }

    // Fungsi sederhana yang hanya melakukan pemetaan tanpa perubahan pada data
    fun getProductsDeferred1(): Single<List<Product>>? {
        return getProducts().map { it }
    }

    // Fungsi yang menggunakan flatMap untuk melakukan pemetaan dengan perubahan data pada produk
    fun getProductsDeferred2(): Single<List<Product>>? {
        return getProducts().flatMap { products ->
            Single.fromCallable {
                products
            }
        }
    }

    // Fungsi yang menggunakan flatMap untuk melakukan pemetaan dengan perubahan data pada produk
    fun getProductsDeferred3(): Single<List<Product>>? {
        return getProducts().flatMap { products ->
            Single.fromCallable {
                products.map { product ->
                    Product(product.id, product.name.lowercase(Locale.getDefault()), product.price * 0.9)
                }
            }
        }
    }

    // Fungsi yang menggunakan map untuk melakukan pemetaan tanpa perubahan pada data
    fun getProductsDeferred4(): Single<List<Product>>? {
        return getProducts().map { it }
    }

    // Fungsi yang menggunakan CoroutineScope.async untuk membuat Deferred dengan CoroutineStart.LAZY
    fun getProductsDeferred5(): Deferred<List<Product>> = CoroutineScope(Dispatchers.IO).async(
        start = CoroutineStart.LAZY
    ) {
        getProducts().await()
    }

    // Fungsi yang menggunakan CoroutineScope.async untuk membuat Deferred dengan penggunaan supervisorScope
    suspend fun getProductsAsync1(): List<Product> {
        return supervisorScope {
            async {
                getProducts().await()
            }.await()
        }
    }

    // Fungsi yang menggunakan supervisorScope untuk menghandle failure secara terpisah
    suspend fun getProductsAsync2(): List<Product> {
        return supervisorScope {
            async {
                getProducts().await()
            }
        }.await()
    }

    // Fungsi yang menggunakan CoroutineScope.async dan runCatching untuk menghandle exception
    suspend fun getProductsAsync3(): List<Product> {
        return runCatching {
            coroutineScope {
                async {
                    getProducts().await()
                }
            }.await()
        }.getOrElse {
            emptyList()
        }
    }

    // Fungsi yang menggunakan GlobalScope.async untuk membuat Deferred
    @OptIn(DelicateCoroutinesApi::class)
    fun getProductsDeferred6(): Deferred<List<Product>> = GlobalScope.async(Dispatchers.IO) {
        getProducts().await()
    }

    // Fungsi yang menggunakan subscribeOn untuk menjalankan pemanggilan asinkron
    fun Single<List<Product>>.toDeferred1(): Deferred<List<Product>> {
        val deferred = CompletableDeferred<List<Product>>()

        this.subscribeOn(Schedulers.io()).subscribe({ result ->
            deferred.complete(result)
        }, { error ->
            deferred.completeExceptionally(error)
        })

        return deferred
    }

    // Fungsi yang menggunakan GlobalScope.async untuk membuat Deferred dengan pemanggilan asinkron
    @OptIn(DelicateCoroutinesApi::class)
    fun Single<List<Product>>.toDeferred2(): Deferred<List<Product>> = GlobalScope.async {
        return@async this@toDeferred2.observeOn(Schedulers.io()).await()
    }

    // Fungsi yang menggunakan CoroutineScope.async untuk membuat Deferred dengan pemanggilan asinkron
    fun getProductsDeferred7(): Deferred<Any> = CoroutineScope(Dispatchers.IO).async {
        runCatching {
            getProducts().await()
        }.getOrElse {
            Result.failure<Any>(Throwable())
        }
    }

    // Fungsi yang menggunakan CoroutineScope.async untuk membuat Deferred dengan pemanggilan asinkron
    suspend fun getProductsAsync4(): List<Product> {
        return CoroutineScope(Dispatchers.IO).async {
            getProducts().await()
        }.await()
    }
}