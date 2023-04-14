package com.baeldung.coroutine.timeouts

import kotlinx.coroutines.TimeoutCancellationException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class TimeoutsDemoTest {

    @Test
    fun when_coroutine_withTimeout_launched_then_it_finishes_with_exception() {
        assertFailsWith<TimeoutCancellationException> {
            TimeoutsDemo().withTimeoutDemo()
        }
    }

    @Test
    fun when_coroutine_withTimeoutOrNull_launched_then_it_finishes_with_no_exception_and_null_value() {
        assertThat(TimeoutsDemo().withTimeoutOrNullDemo()).isNull()
    }

    @Test
    fun when_coroutine_withTimeout_launched_then_it_finishes_with_exception_and_retry_called() {
        assertThat(TimeoutsDemo().usingExceptionHandlingDemo()).isEqualTo("Retrying... Task completed successfully!")
    }

    @Test
    fun when_coroutine_withTimeoutOrNull_launched_then_it_finishes_with_no_exception_and_null_value_handled() {
        assertThat(TimeoutsDemo().usingNullHandlingDemo()).isEqualTo("Task timed out with null!")
    }
}
