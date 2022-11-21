package com.baeldung.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class KotlinConstantUnitTest {
    @Test
    void givenKotlinPropertyInObject_whenAccessingInJava_shouldGetExpectedValue() {
        assertEquals(10, TestKotlinConstantObject.COMPILE_TIME_CONST);
        assertEquals(30, TestKotlinConstantObject.INSTANCE.getRUN_TIME_CONST());
    }

    @Test
    void givenKotlinPropertyInObject_whenWithJvmFieldAnnotation_shouldGetExpectedValue() {
        assertEquals(20, TestKotlinConstantObject.JAVA_STATIC_FINAL_FIELD);
    }

    @Test
    void givenKotlinPropertyInCompanionObject_whenAccessInJava_shouldGetExpectedValue() {
        assertEquals(40, TestKotlinConstantClass.COMPANION_OBJECT_NUMBER);
    }

    @Test
    void givenKotlinPropertyInKtFile_whenAccessInJava_shouldGetExpectedValue() {
        assertEquals(42, KotlinFileKt.VALUE_IN_KT_FILE);
        assertEquals("Hello", KotlinFileKt.getGreeting());
    }

    @Test
    void givenKotlinPropertyInKtFileWithAnnotation_whenAccessInJava_shouldGetExpectedValue() {
        assertEquals(4242, NiceKotlinUtil.VALUE_IN_KT_FILE_WITH_ANNOTATION);
        assertEquals("Hello world", NiceKotlinUtil.greetingFromFileWithAnnotation);
    }
}
