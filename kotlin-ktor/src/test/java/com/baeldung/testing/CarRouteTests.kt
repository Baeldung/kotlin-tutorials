package com.baeldung.testing

import com.baeldung.testing.data.Car
import com.baeldung.testing.data.CarStorageMock
import com.baeldung.testing.plugins.configureContentNegotiation
import com.baeldung.testing.plugins.configureRouting
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.testing.*
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class CarRouteTests {

    @Before
    fun before() {
        CarStorageMock.carStorage.clear()
    }

    @Test
    fun `when get cars then should return a list with cars`() = testApplication {
        val client = configureServerAndGetClient()
        CarStorageMock.carStorage.addAll(
            listOf(
                Car(id = "1", brand = "BMW", price = 10_000.0),
                Car(id = "2", brand = "Audi", price = 11_000.0)
            )
        )

        val response = client.get("/cars")
        val responseBody: List<Car> = response.body()

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(2, responseBody.size)

        val bmwCar = responseBody.find { it.id == "1" }
        assertEquals("BMW", bmwCar?.brand)
        assertEquals(10_000.0, bmwCar?.price)

        val audiCar = responseBody.find { it.id == "2" }
        assertEquals("Audi", audiCar?.brand)
        assertEquals(11_000.0, audiCar?.price)
    }

    @Test
    fun `when get specific car id then should return a that car`() = testApplication {
        val client = configureServerAndGetClient()
        CarStorageMock.carStorage.add(Car(id = "1", brand = "BMW", price = 10_000.0))

        val response = client.get("/cars/1")
        val responseBody: Car = response.body()

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("1", responseBody.id)
        assertEquals("BMW", responseBody.brand)
    }

    @Test
    fun `when get specific car id with wrong id then should return error`() = testApplication {
        val client = configureServerAndGetClient()
        CarStorageMock.carStorage.add(Car(id = "1", brand = "BMW", price = 10_000.0))

        val response = client.get("/cars/3")
        val responseText = response.bodyAsText()

        assertEquals(HttpStatusCode.NotFound, response.status)
        assertEquals("car.not.found", responseText)
    }

    @Test
    fun `when post cars then should create a car and return it`() = testApplication {
        val client = configureServerAndGetClient()

        val response = client.post("/cars") {
            contentType(ContentType.Application.Json)
            setBody(Car(id = "2", brand = "Audi", price = 11_000.0))
        }
        val responseBody: Car = response.body()

        assertEquals(HttpStatusCode.Created, response.status)
        assertEquals("2", responseBody.id)
        assertEquals("Audi", responseBody.brand)
        assertEquals(1, CarStorageMock.carStorage.size)
    }

    @Test
    fun `when put cars then should update a car and return it`() = testApplication {
        val client = configureServerAndGetClient()

        CarStorageMock.carStorage.add(Car(id = "1", brand = "BMW", price = 10_000.0))

        val response = client.put("/cars/1") {
            contentType(ContentType.Application.Json)
            setBody(Car(id = "1", brand = "Audi", price = 11_000.0))
        }
        val responseBody: Car = response.body()

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("1", responseBody.id)
        assertEquals("Audi", responseBody.brand)
        assertEquals("Audi", CarStorageMock.carStorage.find { it.id == "1" }?.brand)
    }

    @Test
    fun `when put cars with wrong id then should return error`() = testApplication {
        val client = configureServerAndGetClient()

        CarStorageMock.carStorage.add(Car(id = "1", brand = "BMW", price = 10_000.0))

        val response = client.put("/cars/2") {
            contentType(ContentType.Application.Json)
            setBody(Car(id = "1", brand = "Audi", price = 11_000.0))
        }

        val responseText = response.bodyAsText()

        assertEquals(HttpStatusCode.NotFound, response.status)
        assertEquals("car.not.found", responseText)
    }

    @Test
    fun `when delete a car then should return ok`() = testApplication {
        val client = configureServerAndGetClient()

        CarStorageMock.carStorage.add(Car(id = "1", brand = "BMW", price = 10_000.0))

        val response = client.delete("/cars/1")

        val responseText = response.bodyAsText()

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("car.deleted", responseText)
    }

    @Test
    fun `when delete a car with wrong id then should return error`() = testApplication {
        val client = configureServerAndGetClient()

        CarStorageMock.carStorage.add(Car(id = "1", brand = "BMW", price = 10_000.0))

        val response = client.delete("/cars/2")

        val responseText = response.bodyAsText()

        assertEquals(HttpStatusCode.NotFound, response.status)
        assertEquals("car.not.found", responseText)

    }

    private fun ApplicationTestBuilder.configureServerAndGetClient(): HttpClient {
        application {
            configureRouting()
            configureContentNegotiation()
        }
        val client = createClient {
            install(ContentNegotiation) {
                jackson()
            }
        }
        return client
    }

}
