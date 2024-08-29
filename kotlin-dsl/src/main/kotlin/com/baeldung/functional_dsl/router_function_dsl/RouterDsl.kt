package com.baeldung.functional_dsl.router_function_dsl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router

@SpringBootApplication
open class Application

fun main(vararg args: String) {
  runApplication<Application>(*args)
  {
    addInitializers(
      beans {
        bean {
          router {
            GET("/endpoint/{country}") { it : ServerRequest ->
              ServerResponse.ok().body(
                mapOf(
                  "name" to it.param("name"),
                  "age" to it.headers().header("X-age")[0],
                  "country" to it.pathVariable("country")
                )
              )
            }
          }
        }
      }
    )
  }
}