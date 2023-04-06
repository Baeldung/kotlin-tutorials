package com.baeldung.coroutine


import org.apache.commons.lang3.RandomStringUtils
import kotlin.coroutines.suspendCoroutine

fun loadData(callback: (result: Result<String>) -> Unit) {
    loadDataFromServer { serverResult ->
        if (serverResult.isFailure) {
            callback(serverResult)
        } else {
            loadDataFromCache { cacheResult ->
                if (cacheResult.isFailure) {
                    callback(cacheResult)
                } else {
                    processResult(serverResult, cacheResult) { processedResult ->
                        saveResult(processedResult) { saveResult ->
                            if (saveResult.isFailure) {
                                callback(saveResult)
                            } else {
                                callback(processedResult)
                            }
                        }
                    }
                }
            }
        }
    }
}

val cache = mutableListOf("initial")

fun saveResult(processedResult: Result<String>, callback: (Result<String>) -> Unit) =
    callback(processedResult.map { it.also(cache::add) })

fun processResult(serverResult: Result<String>, cacheResult: Result<String>, callback: (Result<String>) -> Unit) {
    callback(serverResult.map {
        it.also { result ->
            cache.clear()
            cache.add(result)
        }
    }
        .onFailure {
            if (cacheResult.isSuccess)
                callback(cacheResult)
        })
}

fun loadDataFromCache(callback: (Result<String>) -> Unit): Unit =
    (cache.takeIf { it.isNotEmpty() }?.get(0)?.let { Result.success(it) }
        ?: Result.failure(NullPointerException("No value")))
        .let(callback)

fun loadDataFromServer(callback: (Result<String>) -> Unit) {
    RandomStringUtils.randomAlphanumeric(10).let {
        when {
            it.contains('a') -> callback(Result.failure(IllegalArgumentException("Not a again!")))
            else -> callback(Result.success(it))
        }
    }
}

suspend fun loadDataFromServerSequential(): String =
    suspendCoroutine { loadDataFromServer(it::resumeWith) }

suspend fun loadDataFromCacheSequential(): String = suspendCoroutine { loadDataFromCache(it::resumeWith) }

suspend fun saveResultSequential(processedResult: String): String = suspendCoroutine {
    saveResult(Result.success(processedResult), it::resumeWith)
}

suspend fun processResultSequential(serverResult: String, cacheResult: String) = suspendCoroutine {
    processResult(Result.success(serverResult), Result.success(cacheResult), it::resumeWith)
}

suspend fun loadDataSequential(callback: (result: Result<String>) -> Unit) = try {
    val serverData = loadDataFromServerSequential()
    val cacheData = loadDataFromCacheSequential()
    val processedResult = processResultSequential(serverData, cacheData)
    saveResultSequential(processedResult)
    callback(Result.success(processedResult))
} catch (ex: Exception) {
    callback(Result.failure(ex))
}

