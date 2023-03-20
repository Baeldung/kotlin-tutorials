package com.baeldung.powerofnumber

class PowerOfNumber {

    companion object{
        fun power(baseVal: Int, exponentVal: Int): Long{
            val base = baseVal
            var exponent = exponentVal
            var result: Long = 1

            while (exponent != 0) {
                result *= base.toLong()
                --exponent
            }
            return result
        }

        fun powerRec(baseVal: Int, exponentVal: Int): Long{
            return if (exponentVal != 0) {
                baseVal  * power(baseVal, exponentVal - 1)
            } else
                1
        }
    }
}