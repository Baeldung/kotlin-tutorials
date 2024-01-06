package com.baeldung.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.websocket.*
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import kotlin.test.*

class RequestsUnitTest {

    companion object {

        private val client = HttpClient(mockEngine) {
            expectSuccess = true
            install(ContentNegotiation) {
                jackson()
            }
            install(Auth) {
                basic {
                    credentials {
                        BasicAuthCredentials(username = "baeldung", password = "baeldung")
                    }
                    sendWithoutRequest { _ -> true }
                }
            }
        }

        // Different to the regular client, as that one is using a mock engine
        private val websocketsClient = HttpClient {
            install(WebSockets) {
                contentConverter = JacksonWebsocketContentConverter()
            }
        }

        private val noAuthClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                jackson()
            }
        }

        @JvmStatic
        @AfterClass
        fun afterTests() {
            client.close()
        }
    }

    @Test
    fun `when fetching cars then should get two cars`() {
        runBlocking {
            with(client.get("/cars")) {
                assertEquals(HttpStatusCode.OK, status)
                val cars: List<Car> = body()
                assertEquals(2, cars.size)
                assertTrue { cars.any { car -> car.name == "Car 1" } }
            }
        }
    }

    @Test
    fun `when fetching driver zero then should get driver`() {
        runBlocking {
            with(client.get("/driver?id=0")) {
                assertEquals(HttpStatusCode.OK, status)
                val driver: Driver = body()
                assertEquals(0, driver.id)
            }
        }
    }

    @Test
    fun `when creating car then should succeed`() {
        runBlocking {
            with(client.put("/car") {
                contentType(ContentType.Application.Json)
                setBody(Car(id = 2, name = "Car 3", driver = 1))
            }) {
                assertEquals(HttpStatusCode.OK, status)
                assertEquals("Created!", bodyAsText())
            }
        }
    }

    @Test
    fun `when requesting unknown endpoint then should throw exception`() {
        runBlocking {
            try {
                client.get("/this-does-not-exist")
            } catch (exception: ClientRequestException) {
                return@runBlocking
            }
            fail("Did not throw an exception!")
        }
    }

    @Test
    fun `when creating driver then should succeed`() {
        runBlocking {
            with(client.put("/driver") {
                contentType(ContentType.Application.Json)
                setBody(Driver(id = 2, name = "Jack"))
            }) {
                assertEquals(HttpStatusCode.OK, status)
                assertEquals("Created!", bodyAsText())
            }
        }
    }

    @Test
    fun `when sending websocket messages then should receive them`() {
        val server = startEmbeddedServer()
        runBlocking {
            websocketsClient.webSocket(method = HttpMethod.Get, host = "127.0.0.1", port = 8080, path = "/messages") {
                var message = incoming.receive() as? Frame.Text
                while (message == null) {
                    message = incoming.receive() as? Frame.Text
                }
                assertEquals("Hi!", String(message.data))
                send("Bye!")
            }
        }
        server.stop()
    }

    @Test
    fun `when requesting websocket driver then should receive and deserialize`() {
        val server = startEmbeddedServer()
        runBlocking {
            websocketsClient.webSocket(method = HttpMethod.Get, host = "127.0.0.1", port = 8080, path = "/driver") {
                var message = incoming.receive() as? Frame.Text
                while (message == null) {
                    message = incoming.receive() as? Frame.Text
                }
                assertEquals("Which driver would you like to see?", String(message.data))
                send("0")
                val driver = receiveDeserialized<Driver>()
                assertEquals(0, driver.id)
                send("Bye!")
            }
        }
        server.stop()
    }

    @Test
    fun `when not sending authentication then should not succeed`() {
        runBlocking {
            with(noAuthClient.get("/cars")) {
                assertEquals(HttpStatusCode.Unauthorized, status)
            }
        }
    }

    private fun startEmbeddedServer(): NettyApplicationEngine {
        val env = applicationEngineEnvironment {
            module {
                websocketsModule()
            }
            connector {
                host = "0.0.0.0"
                port = 8080
            }
        }
        val server = embeddedServer(Netty, env)
        server.start(false)
        return server
    }

}