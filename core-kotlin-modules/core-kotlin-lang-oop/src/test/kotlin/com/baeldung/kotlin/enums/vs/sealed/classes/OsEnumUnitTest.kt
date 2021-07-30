package com.baeldung.kotlin.enums.vs.sealed.classes

import org.junit.jupiter.api.Test

class OsEnumUnitTest {
    @Test
    fun detect_os() {
        detectOsByUsingWhen(OsEnum.Linux)
        detectOsByUsingWhen(OsEnum.Windows)
        detectOsByUsingWhen(OsEnum.Mac)
        detectOsByUsingWhen(OsEnum.Unknown)
    }

    private fun detectOsByUsingWhen(osEnum: OsEnum) {
        osEnum.printParent()
        when (osEnum) {
            OsEnum.Linux -> println(osEnum.getText(1))
            OsEnum.Windows -> println(osEnum.getText(2))
            OsEnum.Mac -> println(osEnum.getText(3))
            else -> println("Unknown osEnum: $osEnum ")
        }
    }
}