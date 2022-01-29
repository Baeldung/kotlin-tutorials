package com.baeldung.numeric_strings

import com.baeldung.numeric_strings.NumericStrings.isNumericAllAndIsDigit
import com.baeldung.numeric_strings.NumericStrings.isNumericRegex
import com.baeldung.numeric_strings.NumericStrings.isNumericToX
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class NumericStringsUnitTest {
    @Test
    fun `check if number is string with toX methods`() {
        assertTrue { isNumericToX("11") }
        assertTrue { isNumericToX("-11") }
        assertTrue { isNumericToX("011") }
        assertTrue { isNumericToX("11.0F") }
        assertTrue { isNumericToX("11.0D") }
        assertTrue { isNumericToX("11.234") }
        assertTrue { isNumericToX("11.234e56") }
        assertTrue { isNumericToX("     123      ") }
    }

    @Test
    fun `check if number is string with regex method`() {
        assertTrue { isNumericRegex("11") }
        assertTrue { isNumericRegex("-11") }
        assertTrue { isNumericRegex("011") }
        assertTrue { isNumericRegex("11.0") }
        assertTrue { isNumericRegex("11.234") }
    }

    @Test
    fun `check if number is string with all and isDigit method`() {
        assertTrue { isNumericAllAndIsDigit("11") }
        assertTrue { isNumericAllAndIsDigit("123") }
    }
}
