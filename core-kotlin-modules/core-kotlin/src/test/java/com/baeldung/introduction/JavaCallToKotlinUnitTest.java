package com.baeldung.introduction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaCallToKotlinUnitTest {
    @Test
    public void givenKotlinClass_whenCallFromJava_shouldProduceResults() {
        //when
        int res = new MathematicsOperations().addTwoNumbers(2, 4);

        //then
        assertEquals(6, res);

    }
}
