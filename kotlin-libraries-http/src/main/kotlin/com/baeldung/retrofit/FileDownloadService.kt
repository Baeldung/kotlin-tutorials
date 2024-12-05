package com.baeldung.retrofit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Url
import java.io.File
import java.io.FileOutputStream

// Retrofit service interface
interface FileDownloadService {
    @GET
    suspend fun downloadFile(@Url fileUrl: String): ResponseBody
}

// Function to download a PDF using Retrofit
suspend fun downloadPdfWithRetrofit(url: String, outputFile: File) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://example.com/") // Base URL is required but won't be used with dynamic @Url
        .build()

    val service = retrofit.create(FileDownloadService::class.java)

    withContext(Dispatchers.IO) {
        val responseBody = service.downloadFile(url)

        // Save the file to disk
        responseBody.byteStream().use { inputStream ->
            FileOutputStream(outputFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }

        println("File downloaded successfully to ${outputFile.absolutePath}")
    }
}
