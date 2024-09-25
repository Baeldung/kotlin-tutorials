import com.baeldung.openapi.Product
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import org.junit.After
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class ProductTests {
    @Test
    fun testSwaggerUIStatus() = testApplication {
        val response = client.get("/swagger")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testOpenApiStatus() = testApplication {
        val response = client.get("/openapi")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testGetProduct() = testApplication {
        val response = client.get("/product/1")
        assertEquals(
            """
                {
                    "id": 1,
                    "name": "Laptop",
                    "price": 999.99
                }
            """.trimIndent(), response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testPostProduct() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val response = client.post("/product") {
            contentType(ContentType.Application.Json)
            setBody(Product(3, "Tablet", 299.99))
        }
        assertEquals("Product stored correctly", response.bodyAsText())
        assertEquals(HttpStatusCode.Created, response.status)
    }

    @After
    fun deleteDocsDir() {
        File("docs").deleteRecursively()
    }
}
