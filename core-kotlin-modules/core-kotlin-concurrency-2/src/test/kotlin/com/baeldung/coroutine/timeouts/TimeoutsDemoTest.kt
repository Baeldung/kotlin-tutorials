package com.baeldung.coroutine.timeouts

import org.junit.jupiter.api.Test

class TimeoutsDemoTest {

    @Test
    fun when_coroutine_withTimeout_launched_then_it_finishes_with_exception() {
        TimeoutsDemo().withTimeoutDemo()
    }

    @Test
    fun when_coroutine_withTimeoutOrNull_launched_then_it_finishes_with_no_exception_and_null_value() {
        TimeoutsDemo().withTimeoutOrNullDemo()
    }

    @Test
    fun when_coroutine_withTimeout_launched_then_it_finishes_with_exception_and_retry_called() {
        TimeoutsDemo().usingExceptionHandlingDemo()
    }

    @Test
    fun when_coroutine_withTimeoutOrNull_launched_then_it_finishes_with_no_exception_and_null_value_handled() {
        TimeoutsDemo().usingNullHandlingDemo()
    }
}