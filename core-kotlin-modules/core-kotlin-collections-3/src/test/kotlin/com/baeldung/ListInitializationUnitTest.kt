package com.baeldung

import org.junit.Test
import kotlin.test.assertFails
import kotlin.test.assertTrue

class ListInitializationUnitTest {

    @Test
    fun emptyList() {
        val emptyList = emptyList<String>()
        assertTrue(emptyList.isEmpty(), "List is empty")

        val anotherEmptyList = listOf<String>() //internally uses emptyList()
        assertTrue(anotherEmptyList.isEmpty(), "List is empty")


    }

    @Test
    fun readOnlyList(){
        val readOnlyList = listOf<String>("Jitendra", "Alekar")
        //readOnlyList.add() compile time error
        assert(readOnlyList.size == 2)

        //using another list and spread operator
        val secondList = listOf<String>(*readOnlyList.toTypedArray())

        //from arraylist
        val arrayList = ArrayList<String>()
        with(arrayList){
            add("one")
            add("two")
        }
        val thirdList = arrayList.toList()
        assert(thirdList[0] == "one")
    }

    @Test
    fun readWriteList(){
        var mutableList = mutableListOf<String>()
        mutableList.add("Sydney")
        mutableList.add("Tokyo")
        assert(mutableList.get(0) == "Sydney")
        mutableList = mutableListOf("Paris","London")
        assert(mutableList.get(0) == "Paris")

        var arrList = arrayListOf<Int>()
        arrList.add(1)
        arrList.remove(1)
        assert(arrList.size == 0)
        arrList = arrayListOf(1,2,3,4)
        assert(arrList.size == 4)
    }

    @Test
    fun fromMaps(){
        data class User(val name :String, val address : String?)

        val userAddressMap = mapOf (
            "A" to "India",
            "B" to "Australia",
            "C" to null
        )
        val newList = userAddressMap.toList()
        assert(newList.size == 3)

        val valueList = userAddressMap.values.filterNotNull()
        assert(valueList.size==2)
    }

    @Test
    fun conditionalInitializer(){
        val nonNullList = listOfNotNull("A","B",null)
        assert(nonNullList.size == 2)
    }

    @Test
    fun dynamicBuild(){
        val myList = List(10){
            it.toString()
        }
        println(myList)

        val myList2 = MutableList(10){
            it.toString()
        }
        myList2.add("11")
        println(myList2)
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun build(){
        val students = listOf<String>("Hertz","Jane")
        val myList = buildList<String>(students.size + 1) {
            add("Jitendra")
            addAll(students)
        }
    }

}