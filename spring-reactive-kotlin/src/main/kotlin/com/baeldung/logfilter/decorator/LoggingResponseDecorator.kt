package com.baeldung.logfilter.decorator

import com.baeldung.logfilter.asString
import org.reactivestreams.Publisher
import org.slf4j.Logger
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.http.server.reactive.ServerHttpResponseDecorator
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.io.ByteArrayOutputStream
import java.nio.channels.Channels

class LoggingResponseDecorator internal constructor(val log: Logger, delegate: ServerHttpResponse) : ServerHttpResponseDecorator(delegate) {

    override fun writeWith(body: Publisher<out DataBuffer>): Mono<Void> {
        return super.writeWith(Flux.from(body)
            .doOnNext { buffer: DataBuffer ->
                if (log.isDebugEnabled) {
                    val bodyStream = ByteArrayOutputStream()
                    Channels.newChannel(bodyStream).write(buffer.asByteBuffer().asReadOnlyBuffer())
                    log.debug("{}: {} - {} : {}", "response", String(bodyStream.toByteArray()), "header", delegate.headers.asString())
               }
            })
    }
}