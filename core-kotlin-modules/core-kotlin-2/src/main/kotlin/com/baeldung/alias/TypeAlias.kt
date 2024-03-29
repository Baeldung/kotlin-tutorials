package com.baeldung.alias

import java.util.concurrent.CompletableFuture

typealias CreditCard = String
fun linkCard(card: CreditCard) {
    // omitted
    println(card)
}

fun main() {
    val cc: CreditCard = "1234****"
    linkCard(cc)
    val other = cc.uppercase()
    println("CreditCard uppercase: $other")
    linkCard("1234****")
}

class HttpRequest
class HttpResponse
typealias RequestHandler = (HttpRequest) -> HttpResponse
typealias Predicate<T> = (T) -> Boolean
typealias Completed = CompletableFuture<Map<String, Pair<String, Int>>>