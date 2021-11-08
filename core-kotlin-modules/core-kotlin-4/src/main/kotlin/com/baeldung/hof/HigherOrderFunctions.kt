package com.baeldung.hof

import org.slf4j.LoggerFactory
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

fun performAction(action: () -> Unit) {
    println("Actions before action")
    action()
    println("Actions after action")
}

data class SuppliedType(
    val name: String,
    val weight: Int
)

typealias MySpecialSupplier = () -> SuppliedType

@OptIn(ExperimentalTime::class)
fun supplyWithTiming(supplier: MySpecialSupplier) =
    measureTimedValue(supplier)
        .let {
            println("Invocation took ${it.duration}")
            it.value
        }

fun interface Mapper {
    fun toSupply(name: String, weight: Int): SuppliedType
}

fun verifiedSupplier(name: String, weight: Int, mapper: Mapper): SuppliedType {
    println("Some verification")
    listOf("").map { }
    return mapper.toSupply(name, weight)
}

fun <T> curry(a: T, bifunction: (T, T) -> T): (T) -> T = { b -> bifunction(a, b) }

val logger = LoggerFactory.getLogger("com.baeldung.hof")
val client = HTTPClientStub()
const val CDN_URL = "https://cdn.example.net"

fun unrefactoredFunction(imageUrl: URL, objectId: String) {
    val imageResponse = try {
        clientExecutor.submit(Callable { client.get(imageUrl) })
            .get(10L, TimeUnit.SECONDS)
    } catch (ex: Exception) {
        logger.error("Failed to get image: $imageUrl")
        return
    }

    if (imageResponse.status != 200) {
        logger.error("Image cannot be downloaded: $imageUrl - status {}", imageResponse.status)
        return
    }

    val imageData = imageResponse.receive()

    val cdnResponse = try {
        clientExecutor.submit(Callable { client.put("$CDN_URL/$objectId", imageData) })
            .get(10L, TimeUnit.SECONDS)
    } catch (ex: Exception) {
        logger.error("Failed to put image to CDN: $imageUrl")
        return
    }
}

fun <T> timeout(timeoutSec: Long, supplier: Callable<T>): T =
    clientExecutor.submit(supplier).get(timeoutSec, TimeUnit.SECONDS)

fun <T> successOrLogException(exceptionMsg: String, supplier: () -> T): T? = try {
    supplier()
} catch (ex: Exception) {
    logger.error(exceptionMsg)
    null
}

fun refactoredFunction(imageUrl: URL, objectId: String) {
    val imageResponse = successOrLogException("Failed to get image: $imageUrl") {
        timeout(10L) { client.get(imageUrl) }
    } ?: return

    if (imageResponse.status != 200) {
        logger.error("Image cannot be downloaded: $imageUrl - status {}", imageResponse.status)
        return
    }

    val imageData = imageResponse.receive()

    successOrLogException("Failed to put image to CDN: $imageUrl") {
        timeout(10L) { client.put("$CDN_URL/$objectId", imageData) }
    }
}
