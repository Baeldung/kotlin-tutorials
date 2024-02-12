package com.baeldung.theValueAnnotation

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestConstructor.AutowireMode
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@SpringBootTest
@TestConstructor(autowireMode = AutowireMode.ALL)
class TheValueAnnotationUnitTest(val myMagicBean: MyMagicBean) {

    @Test
    fun `when using value annotations, then expected values should be injected`() {
        with(myMagicBean) {
            assertEquals(42, magicNumber)
            assertEquals("It's a magic string", magicString)
            assertTrue(magicFlag)
        }
    }

    @Test
    fun `when using value annotations with default values, then expected values should be injected`() {
        with(myMagicBean) {
            assertEquals(1024, magicNumberWithDefault)
            assertEquals("It's another magic string", magicStringWithDefault)
        }
    }

    @Test
    fun `when using value annotations with null as default values, then expected values should be injected`() {
        with(myMagicBean) {
            assertEquals("null", stringDefaultLiteralNull)
            assertNull(stringDefaultNull)
        }
    }
}