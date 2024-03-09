package com.baeldung.voidtypes

import org.junit.jupiter.api.Test
import kotlin.test.assertNull
import kotlin.test.assertTrue

class VoidTypesUnitTest {

    // Un-commenting below methods will result into compilation error
    // as the syntax used is incorrect and is used for explanation in tutorial.	

    //    fun returnTypeAsVoidAttempt1(): Void {
    //        println("Trying with Void as return type")
    //    }

    //    fun returnTypeAsVoidAttempt2(): Void {
    //        println("Trying with Void as return type")
    //        return null
    //    }

    fun returnTypeAsVoidSuccess(): Void? {
        println("Function can have Void as return type")
        return null
    }

    fun unitReturnTypeForNonMeaningfulReturns(): Unit {
        println("No meaningful return")
    }

    fun unitReturnTypeIsImplicit() {
        println("Unit Return type is implicit")
    }

    fun alwaysThrowException(): Nothing {
        throw IllegalArgumentException()
    }

    fun invokeANothingOnlyFunction() {
        alwaysThrowException()

        @Suppress("UNREACHABLE_CODE")
        var name = "Tom"
    }

    @Test
    fun givenJavaVoidFunction_thenMappedToKotlinUnit() {
        @Suppress("USELESS_IS_CHECK")
        assertTrue(System.out.println() is Unit)
    }

    @Test
    fun givenVoidReturnType_thenReturnsNullOnly() {
        assertNull(returnTypeAsVoidSuccess())
    }

    @Test
    fun givenUnitReturnTypeDeclared_thenReturnsOfTypeUnit() {
        @Suppress("USELESS_IS_CHECK")
        assertTrue(unitReturnTypeForNonMeaningfulReturns() is Unit)
    }

    @Test
    fun givenUnitReturnTypeNotDeclared_thenReturnsOfTypeUnit() {
        @Suppress("USELESS_IS_CHECK")
        assertTrue(unitReturnTypeIsImplicit() is Unit)
    }
}