package com.baeldung.palindromicstring
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PalindromicStringTest {

    @Test fun `palindrome check using loop function`(){
        assertTrue(isPalindromeUsingLoop("racecar"))
        assertTrue(isPalindromeUsingLoop("redivider"))
        assertFalse(isPalindromeUsingLoop("Palindrome"))
        assertFalse(isPalindromeUsingLoop("sever"))
        assertTrue(isPalindromeUsingLoop("3553"))
        assertTrue(isPalindromeUsingLoop("tattarrattat"))
    }

    @Test fun `palindrome check using built in function`(){
        assertTrue(isPalindromeWithBuiltInFunction("racecar"))
        assertTrue(isPalindromeWithBuiltInFunction("redivider") )
        assertFalse(isPalindromeWithBuiltInFunction("Palindrome") )
        assertFalse(isPalindromeWithBuiltInFunction("sever"))
        assertTrue(isPalindromeWithBuiltInFunction("3553") )
        assertTrue(isPalindromeWithBuiltInFunction("tattarrattat"))
    }

    @Test fun `palindrome check using recursion`(){
        assertTrue(isPalindromeUsingRecursion("racecar"))
        assertTrue(isPalindromeUsingRecursion("redivider"))
        assertFalse(isPalindromeUsingRecursion("Palindrome"))
        assertFalse(isPalindromeUsingRecursion("sever"))
        assertTrue(isPalindromeUsingRecursion("3553"))
        assertTrue(isPalindromeUsingRecursion("tattarrattat"))
    }

    fun isPalindromeUsingLoop(str: String): Boolean {
        var start = 0
        var end = str.length - 1
        while (start < end) {
            if (str[start] != str[end]) {
                return false
            }
            start++
            end--
        }
        return true
    }

    fun isPalindromeWithBuiltInFunction(str: String): Boolean {
        val reversedStr = str.reversed()
        return str == reversedStr
    }

    fun isPalindromeUsingRecursion(str: String): Boolean {
        if (str.length <= 1) {
            return true
        }
        if (str.first() != str.last()) {
            return false
        }
        return isPalindromeUsingRecursion(str.substring(1, str.length - 1))
    }
}