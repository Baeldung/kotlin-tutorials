package com.baeldung.formatting

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.checkAll
import org.assertj.core.api.Assertions.assertThat

class NumberFormatUnitTest : ShouldSpec({

    // Dynamic generation of tests for each implementation
    nameToImplementationPairs.forEach { (name, function) ->

        // Property based test (for each implementation)
        should("return correctly formatted string with $name implementation") {
            checkAll(Arb.positiveInt()) { number ->
                var result = function(number)

                assertThat(result).containsPattern("^(\\d{1,3}(\\.\\d{3})*|\\d+)$")
                assertThat(number.toString()).isEqualTo(result.replace(".", ""))
            }
        }

        givenToExpectedPairs.forEach { (givenNumber, expectedString) ->

            // Parameterised; Example based test
            should("return '$expectedString' for $givenNumber with $name implementation") {
                assertThat(function(givenNumber)).isEqualTo(expectedString)
            }
        }

    }
})

// Examples to check against, with given number and expected string
private val givenToExpectedPairs = listOf(
    0 to "0",
    12 to "12",
    456 to "456",
    100_000 to "100.000",
    1_234_567 to "1.234.567"
)

// Different implementations of the formatting feature with the display name
private val nameToImplementationPairs = listOf(
    "FormatByChunking" to { it: Int -> FormatByChunking.formatted(it) },
    "FormatByStringFormat" to { it: Int -> FormatByStringFormat.formatted(it) },
    "FormatByDecimalFormatGermany" to { it: Int -> FormatByDecimalFormatGermany.formatted(it) },
    "FormatByDecimalFormat" to { it: Int -> FormatByDecimalFormat.formatted(it) }
)
