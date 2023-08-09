package com.baeldung.palindromicstring
import org.junit.jupiter.api.Test

class PalindromicStringTest {

    @Test fun `palindrome check using loop function`(){
        assert(isPalindromeUsingLoop("racecar") == true)
        assert(isPalindromeUsingLoop("redivider") == true)
        assert(isPalindromeUsingLoop("Palindrome") == false)
        assert(isPalindromeUsingLoop("sever") == false)
        assert(isPalindromeUsingLoop("3553") == true)
        assert(isPalindromeUsingLoop("tattarrattat") == true)
    }

    @Test fun `palindrome check using built in function`(){
        assert(isPalindromeWithBuiltInFunction("racecar") == true)
        assert(isPalindromeWithBuiltInFunction("redivider") == true)
        assert(isPalindromeWithBuiltInFunction("Palindrome") == false)
        assert(isPalindromeWithBuiltInFunction("sever") == false)
        assert(isPalindromeWithBuiltInFunction("3553") == true)
        assert(isPalindromeWithBuiltInFunction("tattarrattat") == true)
    }

    @Test fun `palindrome check using recursion`(){
        assert(isPalindromeUsingRecursion("racecar") == true)
        assert(isPalindromeUsingRecursion("redivider") == true)
        assert(isPalindromeUsingRecursion("Palindrome") == false)
        assert(isPalindromeUsingRecursion("sever") == false)
        assert(isPalindromeUsingRecursion("3553") == true)
        assert(isPalindromeUsingRecursion("tattarrattat") == true)
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