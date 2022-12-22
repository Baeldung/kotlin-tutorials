package com.baeldung.runExternalCommand

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class RunExternalCommandUnitTest {

    @Test
    fun `Given a command, When executed with ProcessBuilder, Then it is executed successfully`() {
        val result = ProcessBuilder("java", "-version")
          .redirectOutput(ProcessBuilder.Redirect.INHERIT)
          .redirectError(ProcessBuilder.Redirect.INHERIT)
          .start()
          .waitFor()
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `Given a command, When executed with Runtime, Then it is executed successfully`() {
        val process = Runtime.getRuntime().exec("java -version")
        val result = process.waitFor()
        assertThat(result).isEqualTo(0)
    }
}
