package com.baeldung.coroutine.composing

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URL
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

class DataProcessUseCase(dispatcher: CoroutineDispatcher) : CoroutineScope by CoroutineScope(dispatcher) {
    private val client = HTTPClientMock { url ->
        when (url) {
            urlA -> "Hello, "
            urlB -> "world!"
            else -> throw IllegalArgumentException(url.toString())
        }
    }

    private val asyncDbWriter = AsyncWorker { (input, result) -> storeToDatabase(input, result) }

    fun processCallAsync(userInput: UserInput): Deferred<String> = async {
        val responseA = client.get(urlA, userInput.query)
        val responseB = client.get(urlB, userInput.query)
        (responseA.body + responseB.body)
          .also {
              storeToDatabase(userInput, it)
              logger.info("User input $userInput was processed with the result $it")
          }
    }

    fun processCallEfficientlyAsync(userInput: UserInput): Deferred<String> = async {
        val responseA = async { client.get(urlA, userInput.query) }
        val responseB = async { client.get(urlB, userInput.query) }
        (responseA.await().body + responseB.await().body)
          .also {
              launch(Dispatchers.IO) {
                  storeToDatabase(userInput, it)
              }.join()
              launch {
                  logger.info("User input $userInput was processed with the result $it")
              }
          }
    }

    suspend fun callManyHttpUrlsAtOnce(userInput: UserInput, urls: List<URL>): List<Response> = coroutineScope {
        urls.map { url -> async { client.get(url, userInput.query) } }
          .awaitAll()
    }

    suspend fun callManyHttpUrlsAtOnceIgnoreErrors(userInput: UserInput, urls: List<URL>): List<Response> =
      coroutineScope {
          urls.map { url -> async { runCatching { client.get(url, userInput.query) } } }
            .mapNotNull { deferred ->
                deferred.await().fold(
                  onSuccess = { it },
                  onFailure = {
                      logger.error("Error during one of the calls", it)
                      null
                  }
                )
            }
      }

    suspend fun saveManyResults(results: List<String>, userInput: UserInput) = coroutineScope {
        results.map { launch { storeToDatabase(userInput, it) } }.joinAll()
    }

    private fun storeToDatabase(userInput: UserInput, result: String): Int {
        // Do nothing
        return 1 // Supposed number of changed rows
    }

    fun storeToDatabaseAsync(userInput: UserInput, result: String): CompletableFuture<Int> =
      asyncDbWriter.submit(userInput to result)

    companion object {
        val urlA = URL("https://service-a.domain.dom")
        val urlB = URL("https://service-b.domain.dom")
        val logger: Logger = LoggerFactory.getLogger(DataProcessUseCase.javaClass)
    }
}

data class UserInput(val query: String)

class HTTPClientMock(private val mockAnswering: (URL) -> String) {
    suspend fun get(url: URL, params: String): Response = Response(200, mockAnswering(url))
}

class Response(val status: Int, val body: String)