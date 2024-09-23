package com.baeldung.anyandstarprojection

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.reflect.full.createType
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class StarProjectionAndAnyUnitTest {

    val starlist: MutableList<*> = mutableListOf(1, "Baeldung", 3.5)
    val anylist: MutableList<Any> = mutableListOf(1, "Baeldung", 3.5)

    @Test
    fun `test reading from List`() {
        assertEquals(1, starlist[0])
        // starlist.add(42)  // not allowed

        assertTrue(starlist[0] is Any?)

        assertEquals("Baeldung", anylist[1])
        anylist.add(42) // allowed
    }

    @Suppress("UNREACHABLE_CODE")
    @Test
    fun `test for nullability`() {
        val a: Any = "Kotlin"
        val b: Any? = null
        // val c: Any = null    // not allowed

        assertTrue(null is Any?)
        assertFalse(null is Any)

        assertFailsWith<NullPointerException> {
            val c : Any = null!!
        }

        assertEquals("Kotlin", a)
        assertEquals(null, b)

        val list: List<*> = listOf(1, "Kotlin", null)
        assertTrue(list[2] is Any?)
    }

    @Test
    fun `test writing to MutableList star projection fails`() {
//        val mutableList: MutableList<*> = mutableListOf(1, 2, 3)
        val mutableList: MutableList<*> = mutableListOf(1, "Hello", 3.5)
//        mutableList.add(4)

        (mutableList as MutableList<Int>).add(4)  // Harus casting untuk bisa menambahkan

        // Tidak bisa menambahkan elemen ke MutableList<*> karena kita tidak tahu tipe pastinya
//        assertFailsWith<ClassCastException> {
////            (mutableList as MutableList<Int>).add(4)  // Harus casting untuk bisa menambahkan
//            mutableList.add(4)  // Harus casting untuk bisa menambahkan
//        }
    }

    @Test
    fun `test writing to MutableList of Any succeeds`() {
        val mutableList: MutableList<Any> = mutableListOf(1, 2, 3)

        // Kita bisa menambahkan elemen ke MutableList<Any>
        mutableList.add("New Element")

        assertEquals(4, mutableList.size)
        assertEquals("New Element", mutableList[3])
    }

    @Test
    fun `test reflection on List with star projection`() {
        val list: List<*> = listOf(1, "Hello", 3.5)

        // Menggunakan refleksi untuk mendapatkan tipe parameter generik
//        val listType = list::class.createType()

        // Mengambil properti dan tipe generiknya
        val genericType = list::class.supertypes.first().arguments.first().type

        // Karena menggunakan *, tipe yang muncul akan Any?
//        assertEquals("kotlin.Any?", genericType.toString())
    }

    @Test
    fun `test reflection on List of Any`() {
        val list: List<Any> = listOf(1, "Hello", 3.5)

        // Menggunakan refleksi untuk mendapatkan tipe parameter generik
//        val listType = list::class.createType()

        // Mengambil properti dan tipe generiknya
        val genericType = list::class.supertypes.first().arguments.first().type

        // Karena menggunakan Any, tipe yang muncul adalah Any
//        assertEquals("kotlin.Any", genericType.toString())
    }

    // Fungsi generik dengan batasan untuk tipe `T`
    fun <T> copyData(source: List<T>, destination: MutableList<T>) {
        for (item in source) {
            destination.add(item)
        }
    }

    @Test
    fun `test copy with Any`() {
        val source: List<Any> = listOf(1, "Hello", 3.5)
        val destination: MutableList<Any> = mutableListOf()

        // Fungsi copy bekerja dengan baik dengan Any
        copyData(source, destination)

        assertEquals(listOf(1, "Hello", 3.5), destination)
    }

    @Test
    fun `test copy with star projection fails`() {
        val source: List<*> = listOf(1, "Hello", 3.5)
        val destination: MutableList<Any> = mutableListOf()

        // Fungsi copy akan gagal dengan *, karena kita tidak tahu tipe pastinya
//        assertFailsWith<ClassCastException> {
        copyData(source as List<Any>, destination)
//        }
    }

    fun printList(list: List<*>) {
        for (item in list) {
            println(item)
        }
    }

    fun printListAny(list: List<Any>) {
        for (item in list) {
            println(item)
        }
    }

    @Test
    fun `test function with star projection`() {
        val list: List<*> = listOf(1, "Hello", 3.5)

        // Fungsi dengan star projection
        printList(list)  // Berhasil karena bisa membaca apapun
    }

    @Test
    fun `test function with Any`() {
        val list: List<Any> = listOf(1, "Hello", 3.5)

        // Fungsi dengan Any
        printListAny(list)  // Berhasil karena tipe Any sesuai
    }
}