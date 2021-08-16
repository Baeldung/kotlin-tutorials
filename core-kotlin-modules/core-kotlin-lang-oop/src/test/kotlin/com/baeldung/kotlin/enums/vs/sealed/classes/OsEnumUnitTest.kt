package com.baeldung.kotlin.enums.vs.sealed.classes

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class OsEnumUnitTest {
    @Test
    fun detect_os() {
        detectOsByUsingWhen(OsEnum.Linux)
        detectOsByUsingWhen(OsEnum.Windows)
        detectOsByUsingWhen(OsEnum.Mac)
        detectOsByUsingWhen(OsEnum.Unknown)
    }

    private fun detectOsByUsingWhen(osEnum: OsEnum) {
        when (osEnum) {
            OsEnum.Linux -> assertEquals("Linux by Open-Source - value=1", osEnum.getText(1))
            OsEnum.Windows -> assertEquals("Windows by Microsoft - value=2", osEnum.getText(2))
            OsEnum.Mac -> assertEquals("Mac by Apple - released at 2001", osEnum.getText(3))
            else -> assert(osEnum == OsEnum.Unknown)
        }
    }
}