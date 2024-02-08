package com.baeldung.obtainTheOSName

import org.apache.commons.lang3.SystemUtils
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class ObtainTheOsNameUnitTest {
    private val log = LoggerFactory.getLogger(ObtainTheOsNameUnitTest::class.java)

    @Test
    fun `when read system env properties then get the OS name and version`() {
        val osName = System.getProperty("os.name")
        val osVersion = System.getProperty("os.version")
        assertTrue { osName.isNotBlank() }
        assertTrue { osVersion.isNotBlank() }

        log.info(
            """|
            |OS Information
            |-----------------
            |OS_Name: $osName
            |Version: $osVersion
            |""".trimMargin()
        )
    }

    @Test
    fun `when using when block then the OS gets determined`() {
        val osName = System.getProperty("os.name").lowercase()
        assertTrue { osName.isNotBlank() }

        val result = when {
            "windows" in osName -> "Windows"
            listOf("mac", "nix", "sunos", "solaris", "bsd").any { it in osName } -> "*nix"
            else -> "Other"
        }

        log.info(">>> $osName -> $result")
    }

    @Test
    fun `when using SystemUtils from commons-lang then get the OS name and version`() {
        val osName = SystemUtils.OS_NAME
        val osVersion = SystemUtils.OS_VERSION
        assertTrue { osName.isNotBlank() }
        assertTrue { osVersion.isNotBlank() }

        log.info(
            """|
            |OS_Name   : $osName
            |Version   : $osVersion
            |Is Windows: ${SystemUtils.IS_OS_WINDOWS}
            |Is *nix   : ${SystemUtils.IS_OS_UNIX}
            |Is Mac OS : ${SystemUtils.IS_OS_MAC}
            |""".trimMargin()
        )
    }

}