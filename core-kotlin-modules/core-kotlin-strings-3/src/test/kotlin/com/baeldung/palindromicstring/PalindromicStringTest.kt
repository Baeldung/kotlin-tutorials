package com.baeldung.palindromicstring
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
class PalindromicStringTest {

    @Test fun `palindrome check using loop function`(){
        assert(isPalindrome("racecar") == true)
        assert(isPalindrome("hello") == false)
        assert(isPalindrome("level") == true)
        assert(isPalindrome("madam") == true)
        assert(isPalindrome("3553") == true)
        assert(isPalindrome("123213") == false)
    }

    @Test fun `palindrome check using built in function`(){
        assert(isPalindromeWithBuiltInFunction("racecar") == true)
        assert(isPalindromeWithBuiltInFunction("dadar") == false)
        assert(isPalindromeWithBuiltInFunction("edrarde") == true)
        assert(isPalindromeWithBuiltInFunction("radar") == true)
        assert(isPalindromeWithBuiltInFunction("abababa") == true)
        assert(isPalindromeWithBuiltInFunction("123213") == false)
    }

    @Test fun `palindrome check using recursion`(){
        assert(isPalindromeUsingRecursion("racecar") == true)
        assert(isPalindromeUsingRecursion("Palindrome") == false)
        assert(isPalindromeUsingRecursion("LoL") == true)
        assert(isPalindromeUsingRecursion("madam") == true)
        assert(isPalindromeUsingRecursion("anna") == true)
        assert(isPalindromeUsingRecursion("civic") == true)
    }

    fun isPalindrome(str: String): Boolean {
        var i = 0
        var j = str.length - 1
        while (i < j) {
            if (str[i] != str[j]) {
                return false
            }
            i++
            j--
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
        return isPalindrome(str.substring(1, str.length - 1))
    }
}