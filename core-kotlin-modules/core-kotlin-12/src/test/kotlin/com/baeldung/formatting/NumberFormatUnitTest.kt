package com.baeldung.formatting

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.checkAll
import org.assertj.core.api.Assertions.assertThat

class NumberFormatUnitTest : ShouldSpec({

    // Dynamic generation of tests for each implementation
    nameToImplementationPairs.forEach { (type, function) ->

        // Property based test (for each implementation)
        should("return correctly formatted string by $type") {
            checkAll(Arb.positiveInt()) { number ->
                var result = function(number)

                // Check with regex
                assertThat(result).containsPattern("^(\\d{1,3}(\\.\\d{3})*|\\d+)$")
                // Check against original, by removing the separators
                assertThat(number.toString()).isEqualTo(result.replace(".", ""))

                // Check for general presence & absence of separators
                if (number > 999) assertThat(result).contains(".")
                else assertThat(result).doesNotContain(".")
            }
        }

        givenToExpectedPairs.forEach { (number, expected) ->

            // Parameterised; Example based test
            should("return expected string '$expected' for $number by $type") {
                assertThat(function(number)).isEqualTo(expected)
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
