@file:OptIn(ExperimentalTime::class)

package com.baeldung.kotlin.rxvscoroutines

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.rx2.asObservable
import kotlinx.coroutines.rx2.awaitSingle
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousFileChannel
import java.nio.channels.CompletionHandler
import java.nio.file.StandardOpenOption
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.io.path.Path
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

class RxVersusCoroutinesUnitTest {
    @Test
    fun `when the coroutine sets a timeout then it fails fast`() {
        assertThrows<TimeoutCancellationException> {
            runBlocking {
                withTimeout(100.milliseconds) {
                    delay(3.seconds)
                }
            }
        }
    }

    @Test
    fun `when the code is blocking then the dispatcher stalls`() {
        val starvingContext = coroutineScopeOverSingleThread()
        repeat(3) {
            starvingContext.launch {
                Thread.sleep(30000)
            }
        }
        assertThrows<TimeoutCancellationException> {
            runBlocking {
                withTimeout(100.milliseconds) {
                    starvingContext.launch { println("A quick task which will never execute") }.join()
                }
            }
        }

        val workingContext = coroutineScopeOverSingleThread()
        repeat(3) {
            starvingContext.launch {
                delay(30000) // this function doesn't block
            }
        }
        val result = runBlocking {
            withTimeout(100.milliseconds) {
                withContext(workingContext.coroutineContext) {
                    println("This messages gets to be printed")
                    "Some result"
                }
            }
        }
        assertNotNull(result)
    }

    @Test
    fun `when timeout is used with observables then it also works`() {
        val testSubscriber = TestObserver<Any>()
        val worker = Schedulers.newThread()
        Observable.fromCallable {
            runCatching { Thread.sleep(3000) }
            "result"
        }.timeout(100, TimeUnit.MILLISECONDS)
            .subscribeOn(worker)
            .subscribe(testSubscriber)
        testSubscriber.awaitDone(1, TimeUnit.SECONDS)
        testSubscriber.assertError(TimeoutException::class.java)
        runCatching { worker.shutdown() }
    }

    class CustomException : Exception()

    @Test
    fun `when Rx throws an error then it must be handled explicitly`() {
        val testSubscriber = TestObserver<Any>()
        Observable.error<CustomException>(CustomException()).subscribe(testSubscriber)
        testSubscriber.awaitDone(1, TimeUnit.SECONDS)
        testSubscriber.assertError(CustomException::class.java)
    }

    @Test
    fun `when messages are sent over by channel then they arrive on the other end`() {
        val scopeA = coroutineScopeOverSingleThread()
        val scopeB = coroutineScopeOverSingleThread()
        val pipeline = Channel<String>()
        val results = runBlocking {
            scopeA.launch {
                (1..10).forEach { pipeline.send(it.toString()) }
                pipeline.close()
            }
            withContext(scopeB.coroutineContext) {
                pipeline.consumeAsFlow().map {
                    println("Received message: $it")
                    it
                }.toList()
            }
        }
        assertNotNull(results)
        assertEquals(10, results.size)
    }

    @Test
    fun `when actions are async then they happen in parallel`() {
        val result = runBlocking {
            val a = async { requestOverNetwork(0) }
            val b = async { requestOverNetwork(1) }
            val c = async { requestOverNetwork(2) }
            a.await() + b.await() + c.await()
        }
        assertTrue { result == 3 }
    }

    @Test
    fun `when we need more than one observable and zip them then they are awaited in parallel`() {
        val testSubscriber = TestObserver<Int>()
        Observable.merge(
            listOf(
                observeOverNetwork(0), observeOverNetwork(1), observeOverNetwork(2)
            )
        ).reduce { t1, t2 -> t1 + t2 }.subscribe(testSubscriber)
        testSubscriber.awaitDone(1, TimeUnit.SECONDS)
        testSubscriber.assertValue(3)
    }

    @Test
    fun `when we use async IO functions then coroutines read asynchronously`() {
        val fileChannel =
            AsynchronousFileChannel.open(Path(this::class.java.getResource("test.txt").path!!), StandardOpenOption.READ)
        val buffer = ByteBuffer.allocate(13)
        runBlocking {
            fileChannel.asyncRead(buffer)
        }
        assertEquals("iuK lunhPi!ot", String(buffer.array()))
    }

    @Test
    fun `when we use async IO functions then rx reads asynchronously`() {
        val fileChannel =
            AsynchronousFileChannel.open(Path(this::class.java.getResource("test.txt").path!!), StandardOpenOption.READ)
        val buffer = ByteBuffer.allocate(13)
        val testSubscriber = TestObserver<String>()
        Observable.create { emitter ->
            fileChannel.read(buffer, 0, emitter, object : CompletionHandler<Int, ObservableEmitter<String>> {
                override fun completed(result: Int, attachment: ObservableEmitter<String>) {
                    emitter.onNext(String(buffer.array()))
                    emitter.onComplete()
                }

                override fun failed(exc: Throwable, attachment: ObservableEmitter<String>) {
                    emitter.tryOnError(exc)
                }
            })
        }.subscribe(testSubscriber)
        testSubscriber.awaitDone(1, TimeUnit.SECONDS)
        testSubscriber.assertValue("iuK lunhPi!ot")
    }

    @Test
    fun `when observables are converted to flows then they comply with coroutine rules`() {
        runBlocking {
            val observable = Observable.just("apple")
            val result = observable.awaitSingle()
            assertEquals("apple", result)
        }
        val testSubscriber = TestObserver<Int>()
        runBlocking {
            flow {
                (1..5).forEach { emit(it) }
            }.asObservable(this.coroutineContext).subscribe(testSubscriber)
        }
        testSubscriber.awaitDone(1, TimeUnit.SECONDS)
        testSubscriber.assertValues(1, 2, 3, 4, 5)
    }

    private suspend fun AsynchronousFileChannel.asyncRead(dst: ByteBuffer, position: Long = 0): Int = suspendCoroutine {
        read(dst, position, it, object : CompletionHandler<Int, Continuation<Int>> {
            override fun completed(result: Int, attachment: Continuation<Int>) = it.resume(result)
            override fun failed(exc: Throwable, attachment: Continuation<Int>) = it.resumeWithException(exc)
        })
    }

    private fun observeOverNetwork(order: Int) =
        Observable.just(order).delay(random.nextLong(5, 10), TimeUnit.MILLISECONDS)

    private suspend fun requestOverNetwork(order: Int): Int =
        order.also { delay(random.nextLong(5, 10)) }.also { println("Returning $order") }

    private fun coroutineScopeOverSingleThread() =
        CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher())

    companion object {
        val random = Random((System.currentTimeMillis() % Int.MAX_VALUE).toInt())
    }
}