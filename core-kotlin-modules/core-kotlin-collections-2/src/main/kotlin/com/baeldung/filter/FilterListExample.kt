package com.baeldung.filter

class FilterListExample {

    private val countries = listOf("Germany", "India", "Japan", "Brazil", "Australia")

    fun filterList(): List<String> {
        return countries.filter { it.length > 5 }
    }

    fun filterToList(): List<String> {
        var list = mutableListOf("United States", "Canada")
        return countries.filterTo(list, { it.length > 5 })
    }

    fun filterNotList(): List<String> {
        return countries.filterNot { it.length > 5 }
    }

    fun filterNotToList(): List<String> {
        var list = mutableListOf("United States", "Canada")
        return countries.filterNotTo(list, { it.length > 5 })
    }

    fun filterIndexedList(): List<String> {
        return countries.filterIndexed { index, it -> index != 3 && it.length > 5 }
    }

    fun filterIndexedToList(): List<String> {
        var list = mutableListOf("United States", "Canada")
        return countries.filterIndexedTo(list, { index, it -> index != 3 && it.length > 5 })
    }

    fun filterIsInstanceList(): List<Int> {
        val countryCode = listOf("Germany", 49, null, "India", 91, "Japan", 81, "Brazil", null, "Australia", 61)
        return countryCode.filterIsInstance<Int>()
    }

    fun filterIsInstanceToList(): List<Int> {
        val countryCode = listOf("Germany", 49, null, "India", 91, "Japan", 81, "Brazil", null, "Australia", 61)
        var list = mutableListOf(1, 24)
        return countryCode.filterIsInstanceTo(list)
    }

    fun filterNotNullList(): List<String> {
        val countries = listOf("Germany", "India", null, "Japan", "Brazil", null, "Australia")
        return countries.filterNotNull()
    }

    fun filterNotNullToList(): List<String> {
        val countries = listOf("Germany", "India", null, "Japan", "Brazil", null, "Australia")
        var list = mutableListOf("United States", "Canada")
        return countries.filterNotNullTo(list)
    }
}