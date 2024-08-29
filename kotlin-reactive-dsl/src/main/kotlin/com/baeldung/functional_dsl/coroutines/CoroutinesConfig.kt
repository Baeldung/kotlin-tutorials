package com.baeldung.functional_dsl.coroutines

import com.baeldung.functional_dsl.UsersRepository;
import com.baeldung.functional_dsl.User;
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Import(UsersRepository::class)
@SpringBootApplication
open class AppCoroutines {

  @Autowired
  private lateinit var usersRepository: UsersRepository

  @Bean
  open fun registerForCo() =
    coRouter {
      GET("/users/{id}") {
        println("here we are!")
        val customers : Flow<User> = usersRepository.findUserByIdForCoroutines(
          it.pathVariable("id").toLong()
        )
        ServerResponse.ok().bodyAndAwait(customers)
      }
    }
}

fun main(vararg args: String) {
  runApplication<AppCoroutines>(*args) {}
}