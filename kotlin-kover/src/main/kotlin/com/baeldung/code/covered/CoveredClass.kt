package com.baeldung.code.covered

class CoveredClass {

    fun add(a: Int, b: Int): Int = a + b

    fun multiply(a: Int, b: Int) = a * b

    fun max(a: Int, b: Int) = if(b > a) b else a

    fun min(a: Int, b: Int) = if(b > a) a else b
}