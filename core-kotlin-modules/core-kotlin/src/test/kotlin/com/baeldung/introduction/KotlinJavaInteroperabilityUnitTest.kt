package com.baeldung.introduction

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class KotlinJavaInteroperabilityUnitTest {

    @Test
    fun givenLowercaseString_whenExecuteMethodFromJavaStringUtils_shouldReturnStringUppercase() {
        //given
        val name = "tom"

        //whene
        val res = StringUtils.toUpperCase(name)

        //then
        assertEquals(res, "TOM")
    }
}
