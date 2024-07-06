package com.baeldung.fileupload

import com.github.kittinunf.fuel.core.FileDataPart
import com.github.kittinunf.fuel.httpUpload
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.containing
import com.github.tomakehurst.wiremock.client.WireMock.matching
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import com.github.tomakehurst.wiremock.junit.WireMockRule
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.runBlocking
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.internal.wait
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.Rule
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File
import java.io.IOException

fun uploadFileFuel(filePath: String, uploadUrl: String) {
    val file = File(filePath)
    uploadUrl.httpUpload()
        .add(FileDataPart(file, name = "file"))
        .response { request, response, result ->
            println(response)
        }.get()
}

suspend fun uploadFileKtor(filePath: String, uploadUrl: String) {
    val client = HttpClient(CIO) {}

    val statement: HttpStatement = client.submitFormWithBinaryData(
        url = uploadUrl,
        formData = formData {
            append("file", File(filePath).readBytes(), Headers.build {
                append(HttpHeaders.ContentType, "application/octet-stream")
                append(HttpHeaders.ContentDisposition, "filename=${File(filePath).name}")
            })
        }
    )

    println(statement.execute().readText())
    client.close()
}

fun uploadFileOkHttp(filePath: String, uploadUrl: String) {
    val client = OkHttpClient()
    val file = File(filePath)
    val requestBody = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("file", file.name, file.asRequestBody("application/octet-stream".toMediaTypeOrNull()))
        .build()

    val request = Request.Builder()
        .url(uploadUrl)
        .post(requestBody)
        .build()

    println(client.newCall(request).execute())
}


interface UploadService {
    @Multipart
    @POST("upload")
    fun uploadFile(@Part file: MultipartBody.Part): retrofit2.Call<ResponseBody>
}

fun createUploadService(url: String): UploadService {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(UploadService::class.java)
}

fun uploadFileRetrofit(filePath: String, uploadUrl: String) {
    val file = File(filePath)
    val requestBody = file.asRequestBody("application/octet-stream".toMediaTypeOrNull())
    val multipartBody = MultipartBody.Part.createFormData("file", file.name, requestBody)

    val service = createUploadService(uploadUrl)
    val call = service.uploadFile(multipartBody)
    println(call.execute().body()?.string())
}

class FileUploadUnitTest {
    @Rule
    @JvmField
    val wireMockRule = WireMockRule(8080)

    @BeforeEach
    fun startWireMock() {
        if (wireMockRule.isRunning) return
        wireMockRule.start()
    }
    @AfterEach
    fun stopWireMock() {
        wireMockRule.stop()
    }

    @Test
    fun `Should upload file using Fuel`() {
        wireMockRule.stubFor(post(urlEqualTo("/upload")).willReturn(aResponse().withStatus(200)))

        uploadFileFuel("src/test/resources/testfile.txt", "${wireMockRule.baseUrl()}/upload")

        wireMockRule.verify(
            postRequestedFor(urlEqualTo("/upload")).withHeader("Content-Type", containing("multipart/form-data"))
                .withRequestBody(matching(".*testfile.txt.*"))
        )

    }

    @Test
    fun `Should upload file using Ktor`() = runBlocking {
        wireMockRule.stubFor(post(urlEqualTo("/upload")).willReturn(aResponse().withStatus(200)))

        uploadFileKtor("src/test/resources/testfile.txt", "${wireMockRule.baseUrl()}/upload")

        wireMockRule.verify(postRequestedFor(urlEqualTo("/upload")).withRequestBody(matching(".*testfile.txt.*")).withHeader("Content-Type", containing("multipart/form-data")))
    }

    @Test
    fun `Should upload file using OkHttp`() {
        wireMockRule.stubFor(post(urlEqualTo("/upload")).willReturn(aResponse().withStatus(200)))

        uploadFileOkHttp("src/test/resources/testfile.txt", "${wireMockRule.baseUrl()}/upload")

        wireMockRule.verify(postRequestedFor(urlEqualTo("/upload")).withHeader("Content-Type", containing("multipart/form-data")).withRequestBody(matching(".*testfile.txt.*")))
    }

    @Test
    fun `Should upload file using Retrofit`() {
        wireMockRule.stubFor(post(urlEqualTo("/upload")).willReturn(aResponse().withStatus(200)))

        uploadFileRetrofit("src/test/resources/testfile.txt", wireMockRule.baseUrl())

        wireMockRule.verify(postRequestedFor(urlEqualTo("/upload")).withHeader("Content-Type", containing("multipart/form-data")).withRequestBody(matching(".*testfile.txt.*")))
    }
}