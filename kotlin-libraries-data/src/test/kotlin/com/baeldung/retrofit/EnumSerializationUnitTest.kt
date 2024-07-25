package com.baeldung.retrofit

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

enum class OrderStatus { PENDING, COMPLETED, CANCELLED }

data class Order(val id: String, val status: OrderStatus)

interface OrderService {

    @GET("orders/{id}")
    suspend fun getOrder(@Path("id") orderId: String): Order

    @POST("orders")
    suspend fun createOrder(@Body order: Order): Order
}

val gson: Gson = GsonBuilder().create()
val gsonRetrofit: Retrofit = Retrofit.Builder().baseUrl("http://localhost:8080").addConverterFactory(
    GsonConverterFactory.create(gson)
).build()

val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
val moshiRetrofit: Retrofit = Retrofit.Builder().baseUrl("http://localhost:8080").addConverterFactory(
    MoshiConverterFactory.create(moshi)
).build()

val objectMapper: ObjectMapper = jacksonObjectMapper()
val jacksonRetrofit: Retrofit = Retrofit.Builder().baseUrl("http://localhost:8080").addConverterFactory(
    JacksonConverterFactory.create(objectMapper)
).build()

class EnumSerializationUnitTest {
    @Rule
    @JvmField
    val wireMockRule = WireMockRule(8080)

    @BeforeEach
    fun setup() {
        if(wireMockRule.isRunning) return
        wireMockRule.start()
    }

    @AfterEach
    fun tearDown() {
        wireMockRule.stop()
    }

    @Test
    fun `test Gson default serialization`() = runBlocking {
        val service = gsonRetrofit.create(OrderService::class.java)
        val order = Order("1", OrderStatus.PENDING)
        wireMockRule.stubFor(
            WireMock.post(WireMock.urlEqualTo("/orders"))
                .willReturn(WireMock.aResponse().withBody("""{"id":"1","status":"PENDING"}""").withStatus(200))
        )
        val response = service.createOrder(order)
        assertEquals(order, response.body())
    }

    @Test
    fun `test Moshi default serialization`() = runBlocking {
        val service = moshiRetrofit.create(OrderService::class.java)
        val order = Order("1", OrderStatus.PENDING)
        wireMockRule.stubFor(
            WireMock.post(WireMock.urlEqualTo("/orders"))
                .willReturn(WireMock.aResponse().withBody("""{"id":"1","status":"PENDING"}""").withStatus(200))
        )
        val response = service.createOrder(order)
        assertEquals(order, response.body())
    }

    @Test
    fun `test Jackson default serialization`() = runBlocking {
        val service = jacksonRetrofit.create(OrderService::class.java)
        val order = Order("1", OrderStatus.PENDING)
        wireMockRule.stubFor(
            WireMock.post(WireMock.urlEqualTo("/orders"))
                .willReturn(WireMock.aResponse().withBody("""{"id":"1","status":"PENDING"}""").withStatus(200))
        )
        val response = service.createOrder(order)
        assertEquals(order, response.body())
    }
}