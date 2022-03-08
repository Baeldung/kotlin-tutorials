package com.baeldung.numeric_strings

import com.baeldung.numeric_strings.NumericStrings.ToXTechnique.isNumeric
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class NumericStringsUnitTest {
    @Test
    fun `check if number is string with toX methods`() {
        assertTrue { isNumeric("11") }
        assertTrue { isNumeric("-11") }
        assertTrue { isNumeric("011") }
        assertTrue { isNumeric("11.0F") }
        assertTrue { isNumeric("11.0D") }
        assertTrue { isNumeric("11.234") }
        assertTrue { isNumeric("11.234e56") }
        assertTrue { isNumeric("     123      ") }
    }
}
