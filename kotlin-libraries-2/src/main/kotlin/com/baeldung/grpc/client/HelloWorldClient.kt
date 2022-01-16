package com.baeldung.grpc.client

import com.baeldung.grpc.helloworld.HelloRequest
import com.baeldung.grpc.helloworld.HelloServiceGrpc
import io.grpc.ManagedChannelBuilder

fun helloClient() {
    val channel = ManagedChannelBuilder.forAddress("localhost", 15001)
        .usePlaintext()
        .build()
    val stub = HelloServiceGrpc.newBlockingStub(channel)
    val response = stub.hello(HelloRequest.newBuilder().setName("Baeldung").build())
    println(response.message)
}

fun main(args: Array<String>) {
    helloClient()
}
