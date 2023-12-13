package com.baeldung.util

import com.baeldung.util.ExtensionUtil.isPalindrome
import com.baeldung.util.MathUtils.square
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test


object MathUtils {
    fun square(number: Int): Int {
        return number * number
    }
}

object ExtensionUtil {
    fun String.isPalindrome(): Boolean {
        val cleanString = this.replace("\\s".toRegex(), "").toLowerCase()
        return cleanString == cleanString.reversed()
    }
}

object Utils {
    @JvmStatic
    fun foo(): Boolean {
        return false
    }
}


class UtilTests {
    @Test
    fun `Should Calculate Square`() {
        assertEquals(25, square(5))
        assertEquals(36, square(6))
    }

    @Test
    fun `Should Check If Palindrome`() {
        assertTrue("radar".isPalindrome())
        assertTrue("A man a plan a canal Panama".isPalindrome())
    }
}
