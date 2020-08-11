package com.baeldung.elapsed

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.TimedValue
import kotlin.time.measureTime
import kotlin.time.measureTimedValue
import kotlin.time.milliseconds
import kotlin.time.toDuration

class ElapsedTimeUnitTest {

    @Test
    fun `Given a block of code, When using measureTimeMillis, Then reports the duration as expected`() {
        val elapsed = measureTimeMillis {
            Thread.sleep(100)
            println("Measuring time via measureTimeMillis")
        }

        assertThat(elapsed).isGreaterThanOrEqualTo(100)
    }

    @Test
    fun `Given a block of code, When using measureNanoTime, Then reports the duration as expected in nanoseconds`() {
        val elapsed = measureNanoTime {
            Thread.sleep(100)
            println("Measuring time via measureNanoTime")
        }

        assertThat(elapsed).isGreaterThanOrEqualTo(100 * 1_000_000)
    }


    @Test
    @ExperimentalTime
    fun `Given a block of code, When using measureTime, Then reports the elapsed time as a Duration`() {
        val elapsed: Duration = measureTime {
            Thread.sleep(100)
            println("Measuring time via measureTime")
        }

        assertThat(elapsed).isGreaterThanOrEqualTo(100.milliseconds)
        assertThat(elapsed).isGreaterThanOrEqualTo(100.toDuration(DurationUnit.MILLISECONDS))
    }

    @Test
    @ExperimentalTime
    fun `Given a block of code, When using measureTimedValue, Then returns a value along with the elapsed time`() {
        val (value, elapsed) = measureTimedValue {
            Thread.sleep(100)
            42
        }

        assertThat(value).isEqualTo(42)
        assertThat(elapsed).isGreaterThanOrEqualTo(100.milliseconds)
    }

    @Test
    @ExperimentalTime
    fun `Given a block of code, When using measureTimedValue without destruction, Then returns a value along with the elapsed time`() {
        val timedValue: TimedValue<Int> = measureTimedValue {
            Thread.sleep(100)
            42
        }

        assertThat(timedValue.value).isEqualTo(42)
        assertThat(timedValue.duration).isGreaterThanOrEqualTo(100.milliseconds)
    }

    @Test
    fun `Given any code, When using System nanoTime, Then should be able to calculate elapsed time`() {
        val start = System.nanoTime()
        Thread.sleep(100)

        assertThat(System.nanoTime() - start).isGreaterThanOrEqualTo(100 * 1_000_000)
    }
}
