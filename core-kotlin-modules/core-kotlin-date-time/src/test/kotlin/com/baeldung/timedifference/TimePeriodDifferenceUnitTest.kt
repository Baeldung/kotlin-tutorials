package com.baeldung.timedifference

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource
import kotlinx.datetime.Instant

class TimePeriodDifferenceUnitTest {

    @Test
    fun `given two time marks, when find difference, then give correct time difference`() {
        val timeSource = TimeSource.Monotonic
        val mark1 = timeSource.markNow()
        // call operation to measure duration of
        val mark2 = timeSource.markNow()
        val difference = mark2 - mark1
        assertTrue(difference >= Duration.ZERO);
    }

    @Test
    fun `given two duration periods, when find difference using minus method, then give correct duration value`() {
        val startDuration = 1.days + 12.hours + 15.minutes
        val endDuration = 8.hours + 45.minutes
        val difference: Duration = startDuration.minus(endDuration)
        assertEquals(1.days + 3.hours + 30.minutes, difference)
    }

    @Test
    fun `given two epoch values, when find difference using Instant creation, then give correct duration value`() {
        val startEpochMillis = 1700617592000L
        val endEpochMillis = 1700617692000L
        val instant1 = Instant.fromEpochMilliseconds(startEpochMillis)
        val instant2 = Instant.fromEpochMilliseconds(endEpochMillis)
        val difference = instant2.minus(instant1)
        assertEquals(1.minutes + 40.seconds, difference)
    }
}