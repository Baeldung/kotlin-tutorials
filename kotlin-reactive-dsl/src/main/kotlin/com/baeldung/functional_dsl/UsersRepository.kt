package com.baeldung.functional_dsl

import java.lang.IllegalArgumentException
import java.util.function.Consumer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UsersRepository {

  private val userStorage: MutableMap<Long, User>

  init {
    userStorage = mutableMapOf(
      1L to User(1L, "Alex", 18, "Malta"),
      2L to User(2L, "Eugen", 36, "Austria"),
      3L to User(3L, "Jenny", 12, "Canada")
    )
  }

  fun findUserById(id: Long) : Mono<User> {
    return Mono
      .fromCallable { userStorage[id] }
      .handle { value, sink ->
        if (value == null) {
          sink.error(IllegalArgumentException())
        } else {
          sink.next(value)
        }
      }
  }

  fun createUsers(user: Mono<User>) {
    user.doOnNext {
      userStorage[it.userId] = it
    }.subscribe()
  }

  suspend fun findUserByIdForCoroutines(id: Long) : Flow<User> {
    return flow {
      val user = userStorage[id] ?: User(0L, "Name", 0, "USA")
      emit(user)
    }
  }
}
