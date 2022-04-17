package com.baeldung.grpc.server

import com.baeldung.grpc.helloworld.HelloReply
import com.baeldung.grpc.helloworld.HelloRequest
import com.baeldung.grpc.helloworld.HelloServiceGrpcKt
import io.grpc.ServerBuilder

class HelloService : HelloServiceGrpcKt.HelloServiceImplBase() {
    override suspend fun hello(request: HelloRequest): HelloReply {
        return HelloReply.newBuilder()
            .setMessage("Hello, ${request.name}")
            .build()
    }
}

fun helloServer() {
    val helloService = HelloService()
    val server = ServerBuilder
        .forPort(15001)
        .addService(helloService)
        .build()

    Runtime.getRuntime().addShutdownHook(Thread {
        server.shutdown()
        server.awaitTermination()
    })

    server.start()
    server.awaitTermination()
}

fun main(args: Array<String>) {
    helloServer()
}