package com.baeldung.regex

import org.junit.jupiter.api.Test
import java.util.regex.Pattern
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class MatchingAtSpecificIndexUnitTest {

    @Test
    fun when_matches_at_position_then_returns_true() {
        val stringToBeMatched = "The quick brown fox"
        val versionRegex = "f.x".toRegex()
        assertFalse(versionRegex.matchesAt(stringToBeMatched, 0))
        assertTrue(versionRegex.matchesAt(stringToBeMatched, 16))
    }

    @Test
    fun when_using_jvm_then_java_stdlib_is_enough() {
        val regex = "The"
        val stringToBeMatched = "The quick brown fox"
        assertTrue(Pattern.compile(regex).matcher(stringToBeMatched).lookingAt())
    }

    @Test
    fun when_using_kotlin_then_it_is_much_shorter() {
        val regex = "The"
        val stringToBeMatched = "The quick brown fox"
        assertNotNull(regex.toRegex().find(stringToBeMatched))
    }

    @Test
    fun when_primitive_tokenizing_required_then_matching_at_position_is_useful() {
        val actualUrl = "jdbc:postgresql://example.cs945smhrv09.us-west-2.rds.amazonaws.com:5423/ciadb"
        val protocolExpression = "jdbc|r2dbc".toRegex()
        val protocol = protocolExpression.matchAt(actualUrl, 0)?.value
        val rdbmsExpression = "mysql|postgresql|oracle".toRegex()
        val rdbms = rdbmsExpression.matchAt(actualUrl, actualUrl.indexOf(':', 0) + 1)?.value
        // loadDriver(protocol, rdbms)
        assertEquals("jdbc", protocol)
        assertEquals("postgresql", rdbms)
    }
}