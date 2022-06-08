package com.baeldung.outside.protectedmodifier

import com.baeldung.protectedmodifier.InternalClass
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class AccessInternalClassTest {

    @Test
    fun whenCallInternalClass_thenItWorks(){
        val internalClass = InternalClass()
        assertThat(internalClass.helloFromInternalFunction()).isEqualTo("Hello")
    }
}