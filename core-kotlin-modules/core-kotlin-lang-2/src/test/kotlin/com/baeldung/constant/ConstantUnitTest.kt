package com.baeldung.constant

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ConstantUnitTest {

    @Test
    fun givenConstant_whenCompareWithActualValue_thenReturnTrue() {
        // in kotlin object
        assertEquals(10, TestKotlinConstantObject.COMPILE_TIME_CONST)
        assertEquals(30, TestKotlinConstantObject.RUN_TIME_CONST)
        assertEquals(20, TestKotlinConstantObject.JAVA_STATIC_FINAL_FIELD)

        // in companion object
        assertEquals(40, TestKotlinConstantClass.COMPANION_OBJECT_NUMBER)

        //in kt file
        assertEquals(42, VALUE_IN_KT_FILE)
        assertEquals("Hello", greeting)

        //in kt file with JvmName annotation
        assertEquals(4242, VALUE_IN_KT_FILE_WITH_ANNOTATION)
        assertEquals("Hello world", greetingFromFileWithAnnotation)
    }
}

