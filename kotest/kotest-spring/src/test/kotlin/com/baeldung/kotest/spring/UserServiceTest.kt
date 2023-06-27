package com.baeldung.kotest.spring

import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest(classes = [UserService::class])
class UserServiceTest : FunSpec() {

    @MockBean
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userService: UserService

    init {
        extension(SpringExtension)

        test("Get user by id should return the user") {
            val userId = 1L
            val expectedUser = User(userId, "John Doe")

            // Mock the UserRepository behavior
            given(userRepository.findUserById(1)).willReturn(expectedUser)

            val result = userService.findUserById(userId)

            result shouldBe expectedUser
        }
    }
}