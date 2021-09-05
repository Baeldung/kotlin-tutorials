package com.baeldung.logfilter

import com.baeldung.logfilter.decorator.LoggingWebExchange
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain

@Component
class LoggingWebFilter : WebFilter {
    @Autowired
    lateinit var log: Logger

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain) = chain.filter(LoggingWebExchange(log, exchange))
}