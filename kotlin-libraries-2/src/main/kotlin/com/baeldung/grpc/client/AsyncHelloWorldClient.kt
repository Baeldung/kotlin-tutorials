package com.baeldung.grpc.client

import com.baeldung.grpc.helloworld.HelloReply
import com.baeldung.grpc.helloworld.HelloRequest
import com.baeldung.grpc.helloworld.HelloServiceGrpc
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver

fun asyncHelloClient() {
    val channel = ManagedChannelBuilder.forAddress("localhost", 15001)
        .usePlaintext()
        .build()
    HelloServiceGrpc.newStub(channel).hello(
        HelloRequest.newBuilder().setName("Baeldung").build(), object : StreamObserver<HelloReply> {
            override fun onNext(response: HelloReply?) {
                println(response?.message)
            }

            override fun onError(throwable: Throwable?) {
                throwable?.printStackTrace()
            }

            override fun onCompleted() {
                println("Completed!")
            }
        }
    )
}

fun main(args: Array<String>) {
    asyncHelloClient()
}
