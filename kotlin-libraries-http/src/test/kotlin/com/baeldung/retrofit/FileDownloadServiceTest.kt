package com.baeldung.retrofit

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import kotlin.test.assertTrue

class FileDownloadServiceTest {

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test successful file download`(): Unit = runBlocking {
        // Mock response with sample PDF content
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("This is a mock PDF content") // Simulate PDF binary content
        mockWebServer.enqueue(mockResponse)

        // Get the mock server URL
        val mockUrl = mockWebServer.url("/sample.pdf").toString()

        // File to store the downloaded content
        val outputFile = File("test_sample.pdf")
        if (outputFile.exists()) {
            outputFile.delete()
        }

        // Call the download function
        downloadPdfWithRetrofit(mockUrl, outputFile)

        // Verify the file was created and content matches

        assertTrue(outputFile.exists())
        assertTrue(outputFile.readText() == "This is a mock PDF content")

        // Clean up the test file
        outputFile.delete()
    }

}