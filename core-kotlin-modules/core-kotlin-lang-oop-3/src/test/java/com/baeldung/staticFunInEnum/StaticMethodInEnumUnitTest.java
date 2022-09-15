package com.baeldung.staticFunInEnum;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.baeldung.staticFunInEnum.MagicNumber.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StaticMethodInEnumUnitTest {
    @Test
    void givenKotlinEnum_WhenCallStaticMethod_ShouldGetExpectedResult() {
        // the greaterThan() function with @JvmStatic
        assertEquals(Arrays.asList(THREE, FOUR, FIVE, SIX), MagicNumber.greaterThan(2));

        // calling it through the Companion object
        assertEquals(Arrays.asList(THREE, FOUR, FIVE, SIX), MagicNumber.Companion.greaterThan(2));

        // the pickOneRandomly() function without @JvmStatic
        assertNotNull(MagicNumber.Companion.pickOneRandomly());
    }
}
