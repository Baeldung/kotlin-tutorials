package com.baeldung.coroutine.launch

import org.junit.jupiter.api.Test

internal class LaunchAndAsyncDemoUnitTest {

    @Test
    fun when_coroutine_is_launched_then_it_is_eagerly_executed_in_dispatcher_thread() {
        LaunchAndAsyncDemo().simpleLaunch()
    }

    @Test
    fun when_launched_in_different_scopes_then_run_in_different_pools() {
        LaunchAndAsyncDemo().launchSeveralCoroutinesInDifferentScopes()
    }

    @Test
    fun when_launched_with_async_then_produces_results() {
        LaunchAndAsyncDemo().launchCoroutineForAResult()
    }

    @Test
    fun when_launched_then_lazy_coroutine_is_executed_last() {
        LaunchAndAsyncDemo().asyncWithLazy()
    }
}