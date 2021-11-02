package com.baeldung.bitwise

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class BitwiseOperatorsUnitTest {

    @Test
    fun given_two_binaries_when_they_are_conjuncted_then_only_bits_set_in_both_survive() {
        val a = 0b10011 // 19
        val b = 0b11110 // 30
        assertEquals(0b10010, a and b) // 18
    }

    @Test
    fun given_two_binaries_when_they_are_disjuncted_then_bits_from_both_appear_in_result() {
        val a = 0b101001 // 41
        val b = 0b110011 // 51
        assertEquals(0b111011, a or b)
    }

    @Test
    fun given_two_binaries_when_they_are_exlusively_disjuncted_then_only_bits_set_differently_survive() {
        val a = 0b110101
        val b = 0b101010
        assertEquals(31, a xor b)
    }

    @Test
    fun given_a_binary_when_it_is_inverted_then_all_bits_flip() {
        assertEquals(0b010011, 0b101100.inv() and 0b111111)
    }

    @Test
    fun given_a_binary_when_it_is_shifted_left_2_bits_then_first_two_bits_become_zero() {
        assertEquals(0b11001100, 0b110011 shl 2)
    }


    @Test
    fun given_a_negative_binary_when_it_is_unsigned_shifted_right_then_the_sign_will_be_lost() {
        assertEquals(0b1111111111, -0b1100110011 ushr 22)
    }

    @Test
    fun given_a_negative_binary_when_it_is_signed_shifted_right_then_the_sign_will_be_preserved() {
        assertEquals(0b11001100, 0b1100110011 shr 2) // the first 22 bytes are zeroes
        // the first bit is 1, it means that the number is negative
        assertEquals(0b111111111111111111111100110011, 0b11111111111111111111110011001101 shr 2)
    }

    @Test
    fun given_an_integer_when_shifted_right_then_it_is_divided_n_times_by_two() {
        assertEquals(3, 12 shr 2) // 12 / 2^2 == 12 / 4
    }

    @Test
    fun given_an_integer_when_shifted_left_then_it_is_multiplied_n_times_by_two() {
        assertEquals(24, 3 shl 3) // 3 * 2^3 == 3 * 8
    }

    @Test
    fun given_a_mask_and_a_bitmap_when_conjuncted_then_specific_bit_is_checked() {
        val SKY_IS_BLUE_MASK = 0b00000000000001000000000000

        fun isSkyBlue(worldProperties: Int): Boolean =
            worldProperties and SKY_IS_BLUE_MASK != 0

        assertTrue(isSkyBlue(0b10011100111101011101010101))
    }


    @Test
    fun when_disjuncted_then_set_bits_are_united() {
        val SKY_IS_BLUE = 0b00000000000001000000000000
        val SUN_IS_SHINING = 0b00000000000000100000000000
        val skyIsBlueAndSunShines = SKY_IS_BLUE or SUN_IS_SHINING
        assertEquals(0b00000000000001100000000000, skyIsBlueAndSunShines)
    }
}
