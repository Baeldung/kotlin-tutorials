package com.baeldung.streamtofile

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class InputStreamToFileUnitTest {

    private val content = "Hello World".repeat(1000)

    @Test
    fun `copyTo should copy the input stream to any output stream`() {
        val file: File = createTempFile()
        val inputStream = ByteArrayInputStream(content.toByteArray())

        inputStream.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        assertThat(file).hasContent(content)
    }

    @Test
    fun `copyTo should copy the input stream to any output stream (customize buffering)`() {
        val file: File = createTempFile()
        val inputStream = ByteArrayInputStream(content.toByteArray())

        inputStream.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output, 16 * 1024)
            }
        }

        assertThat(file).hasContent(content)
    }

    @Test
    fun `Files$copy should copy any source to output stream`() {
        Files.deleteIfExists(Paths.get("./copied"))
        val inputStream = ByteArrayInputStream(content.toByteArray())

        inputStream.use { input ->
            Files.copy(input, Paths.get("./copied"))
        }

        assertThat(File("./copied")).hasContent(content)
        Files.deleteIfExists(Paths.get("./copied"))
    }

    @Test
    fun `transferTo should copy any input stream to output stream`() {
        val file = createTempFile()
        val inputStream = ByteArrayInputStream(content.toByteArray())

        inputStream.use { input ->
            file.outputStream().use { output ->
                // uncomment on Java 9
                // input.transferTo(output)
            }
        }

        // assertThat(file).hasContent(content)
    }
}
