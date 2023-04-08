package com.baeldung.coroutine.timeouts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TimeoutAndResourceDemoTest {

    @Test
    fun when_resource_created_inside_timeout_block_then_acquired_value_is_not_always_0() {
        assertThat(acquireAndReleaseWithLeak()).isNotEqualTo(0)
    }

    @Test
    fun when_resource_accessed_by_reference_inside_timeout_block_then_acquired_value_is_always_0() {
        assertThat(acquireAndReleaseWithoutLeak()).isEqualTo(0)
    }
}
