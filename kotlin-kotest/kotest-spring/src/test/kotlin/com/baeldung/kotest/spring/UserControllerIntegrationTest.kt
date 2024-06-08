package com.baeldung.kotest.spring

import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.extensions.spring.testContextManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@WebMvcTest(controllers = [UserController::class])
@ContextConfiguration(classes = [UserController::class, UserService::class, UserRepository::class])
class UserControllerIntegrationTest : FunSpec() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var userRepository: UserRepository

    init {
        extension(SpringExtension)

        beforeTest {
            userRepository.save(User(1, "John Doe"))
        }

        test("Get /users/{id} should return the user") {
            mockMvc.get("/users/1").andExpect {
                status { isOk() }
                jsonPath("\$.id") { value(1) }
                jsonPath("\$.name") { value("John Doe") }
            }.andReturn()
        }
    }
}
