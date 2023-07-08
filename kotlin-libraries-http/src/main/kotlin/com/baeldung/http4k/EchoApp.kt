package com.baeldung.http4k

import io.github.resilience4j.bulkhead.Bulkhead
import io.github.resilience4j.bulkhead.BulkheadConfig
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED
import io.github.resilience4j.ratelimiter.RateLimiter
import io.github.resilience4j.ratelimiter.RateLimiterConfig
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.INTERNAL_SERVER_ERROR
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.filter.ResilienceFilters
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import java.lang.Thread.sleep
import java.time.Duration
import java.util.*

val circuitBreaker: CircuitBreaker = CircuitBreaker.of(
  "circuit",
  CircuitBreakerConfig.custom()
    .slidingWindow(1, 1, COUNT_BASED)
    .failureRateThreshold(100f)
    .permittedNumberOfCallsInHalfOpenState(2)
    .waitDurationInOpenState(Duration.ofSeconds(10))
    .build()
)

val bulkhead: Bulkhead = Bulkhead.of("bulkead", BulkheadConfig.custom().maxConcurrentCalls(1).build())

val circuitBreakerEndpointResponses = ArrayDeque<Response>().apply {
  add(Response(OK))
  add(Response(OK))
  add(Response(INTERNAL_SERVER_ERROR))
  add(Response(OK))
}

val rateLimitingConfig: RateLimiterConfig = RateLimiterConfig.custom()
  .limitRefreshPeriod(Duration.ofMinutes(1))
  .limitForPeriod(1)
  .timeoutDuration(Duration.ofMillis(10))
  .build()

val echoHandler = { req: Request ->
  Response(OK).body(req.body)
}

val app: HttpHandler = routes(
  "/echo" bind POST to ResilienceFilters.RateLimit(RateLimiter.of("echo-rate-limit", rateLimitingConfig))
    .then(echoHandler),

  "/ping" bind GET to {
    Response(OK).body("pong")
  },

  "/resilience" bind GET to
    ResilienceFilters.CircuitBreak(circuitBreaker).then {
      try {
        circuitBreakerEndpointResponses.pop()
      } catch (e: Throwable) {
        Response(INTERNAL_SERVER_ERROR)
      }
    },

  "/bulkhead" bind GET to ResilienceFilters.Bulkheading(bulkhead).then {
    sleep(5000L)
    Response(OK)
  }
)

fun main() {
  val printingApp: HttpHandler = PrintRequest().then(app)

  val server = printingApp.asServer(Jetty(8081)).start()

  println("Server started on " + server.port())
}
