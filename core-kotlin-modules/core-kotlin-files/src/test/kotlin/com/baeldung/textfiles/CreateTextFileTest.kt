package com.baeldung.textfiles

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.File

private const val testPath = "src/test/resources"

class CreateTextFileTest {

    @Test
    fun `create text file directly using writeText`() {
        val file = File("$testPath/write_example.txt")
        file.writeText("Hello, World!")
        assertThat(file).exists()
        assertThat(file).hasContent("Hello, World!")
    }

    @Test
    fun `create text file using PrintWriter`() {
        val file = File("$testPath/print_example.txt")
        file.printWriter().use {
            it.println("Hello, World!")
            it.println("Hello, World!")
        }
        assertThat(file).exists()
        assertThat(file).hasContent("Hello, World!\nHello, World!")
    }

    @Test
    fun `create text file using FileOutputStream`() {
        val file = File("$testPath/file_output_example.txt")
        file.outputStream().use {
            val text = "Hello, World!"
            it.write(text.toByteArray())
        }
        assertThat(file).exists()
        assertThat(file).hasContent("Hello, World!")
    }

}
