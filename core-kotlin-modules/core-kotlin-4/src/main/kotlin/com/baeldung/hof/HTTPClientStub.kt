package com.baeldung.hof

import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

val clientExecutor: ExecutorService = Executors.newSingleThreadExecutor()

val image = "CDBmleEGw\"V.\t_1᪉-nm@FiUsQuGҘ*-*bQӘ4?`q;?7y1Z~ᡷ}V=BX!%S>TtecHkmdXhi3".encodeToByteArray()

class HTTPClientStub {
    fun get(imageUrl: URL): Response = Response(200) { image }
    fun put(url: String, body: ByteArray) {

    }
}

class Response(val status: Int, private val receiver: () -> ByteArray) {
    fun receive() =
        receiver()
}
