package com.baeldung.logfilter.decorator

import org.slf4j.Logger
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.ServerWebExchangeDecorator

class LoggingWebExchange(log: Logger, delegate: ServerWebExchange) : ServerWebExchangeDecorator(delegate) {
    private val requestDecorator: LoggingRequestDecorator = LoggingRequestDecorator(log, delegate.request)
    private val responseDecorator: LoggingResponseDecorator = LoggingResponseDecorator(log, delegate.response)
    override fun getRequest(): ServerHttpRequest {
        return requestDecorator
    }

    override fun getResponse(): ServerHttpResponse {
        return responseDecorator
    }
}