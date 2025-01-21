package com.baeldung.mono

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.util.concurrent.atomic.AtomicInteger

class MonoUnitTest {

  @Test
  fun `mapping function is skipped for empty Mono`() {
    val callCount = AtomicInteger(0)
    val emptyMono = Mono.empty<String>()
    val resultMono = emptyMono.map {
      callCount.incrementAndGet()
      it.uppercase()
    }

    StepVerifier.create(resultMono).verifyComplete() // No value emitted, Mono completes directly 
    Assertions.assertEquals(0, callCount.get())
  }

  @Test
  fun `should provide default value for empty Mono`() { 
    val emptyMono = Mono.empty<String>() 
    val resultMono = emptyMono.defaultIfEmpty("Default Value") 
    StepVerifier.create(resultMono) 
      .expectNext("Default Value")
      .verifyComplete() 
  }

  @Test 
  fun `should not override value for non-empty Mono`() { 
    val nonEmptyMono = Mono.just("Actual Value")
    val resultMono = nonEmptyMono.defaultIfEmpty("Default Value") 
    StepVerifier.create(resultMono) 
      .expectNext("Actual Value")
      .verifyComplete() 
  }

  @Test fun `should detect empty Mono using hasElement`() { 
    val emptyMono = Mono.empty<String>() 
    StepVerifier.create(emptyMono.hasElement()) 
      .expectNext(false) 
      .verifyComplete() 
  }

  @Test fun `should detect non-empty Mono using hasElement`() { 
    val nonEmptyMono = Mono.just("Hello, World!")
    StepVerifier.create(nonEmptyMono.hasElement())
      .expectNext(true)
      .verifyComplete() 
  }

  @Test 
  fun `should provide fallback value for empty Mono`() { 
    val emptyMono = Mono.empty<String>() 
    val fallbackMono = emptyMono.switchIfEmpty(Mono.just("Fallback Value"))
    StepVerifier.create(fallbackMono) 
      .expectNext("Fallback Value")
      .verifyComplete() 
  }

  @Test 
  fun `should not invoke fallback for non-empty Mono`() { 
    val nonEmptyMono = Mono.just("Hello, World!") 
    val resultMono = nonEmptyMono.switchIfEmpty(Mono.just("Fallback Value")) 
    StepVerifier.create(resultMono) 
      .expectNext("Hello, World!") 
      .verifyComplete() 
  }
}
