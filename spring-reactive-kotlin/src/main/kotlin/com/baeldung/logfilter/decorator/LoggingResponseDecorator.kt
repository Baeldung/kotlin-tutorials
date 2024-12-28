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
import java.nio.charset.StandardCharsets

class LoggingResponseDecorator internal constructor(val log: Logger, delegate: ServerHttpResponse) : ServerHttpResponseDecorator(delegate) {
    override fun writeWith(body: Publisher<out DataBuffer>): Mono<Void> {
        if (!log.isDebugEnabled)
            {
                return super.writeWith(body)
            }

        val responseBody = StringBuilder()

        return super.writeWith(
            Flux.from(body)
                .doOnNext { buffer: DataBuffer ->
                    try {
                        val bodyStream = ByteArrayOutputStream()
                        Channels.newChannel(bodyStream).write(buffer.asByteBuffer().asReadOnlyBuffer())
                        responseBody.append(String(bodyStream.toByteArray(), StandardCharsets.UTF_8))
                    } catch (e: Exception) {
                        log.error("Error while processing response body", e)
                    }
                }
                .doOnComplete {
                    log.debug(
                        "{}: {} - {} : {}",
                        "response",
                        responseBody.toString().trim(),
                        "header",
                        delegate.headers.asString(),
                    )
                }
                .then(Mono.fromRunnable {}),
        )
    }
}
