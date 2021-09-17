package com.baeldung.sealedclasses.vs.enums

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class OsEnumUnitTest {
    @Test
    fun detect_os() {
        OsEnum.values().forEach {
            detectOsByUsingWhen(it)
        }
    }

    private fun detectOsByUsingWhen(osEnum: OsEnum) {
        assertEquals("Called from parent enum class", osEnum.getTextParent())
        when (osEnum) {
            OsEnum.Linux -> assertEquals("Linux by Open-Source - value=1", osEnum.getText(1))
            OsEnum.Windows -> assertEquals("Windows by Microsoft - value=2", osEnum.getText(2))
            OsEnum.Mac -> assertEquals("Mac by Apple - released at 2001", osEnum.getText(3))
            else -> assertTrue(osEnum == OsEnum.Unknown)
        }
    }

    private fun detectOsByUsingWhenPrint(osEnum: OsEnum) {
        when (osEnum) {
            OsEnum.Linux -> println("${osEnum.company} - Linux Operating System")
            OsEnum.Mac -> println("${osEnum.company} - Mac Operating System")
            OsEnum.Windows -> println("${osEnum.company} - Windows Operating System")
            else -> println(osEnum.company)
        }
    }
}
