package com.baeldung.retrofit

import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.File
import kotlin.test.assertTrue

class FileDownloadServiceTest {

    @Test
    fun `test successful file download`(): Unit = runBlocking {

        // File to store the downloaded content
        val outputFile = File("test_sample.pdf")
        if (outputFile.exists()) {
            outputFile.delete()
        }

        // Call the download function
        downloadPdfWithRetrofit("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", outputFile)

        // Verify the file was created
        assertTrue(outputFile.exists())

        // Clean up the test file
        outputFile.delete()
    }
}