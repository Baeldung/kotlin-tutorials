package com.baeldung.loops

fun main() {

    val capitalCityByCountry = mapOf("Netherlands" to "Amsterdam",
                                     "Germany" to "Berlin",
                                     "USA" to "Washington, D.C.")

    // Iterate over a map using the for loop

    // Iterate over the entries
    for (entry in capitalCityByCountry) {
        println("The capital city of ${entry.key} is ${entry.value}")
        entry.component1()
    }
    // Iterate over keys
    for (country in capitalCityByCountry.keys) {
        println(country)
    }
    // Iterate over values
    for (capitalCity in capitalCityByCountry.values) {
        println(capitalCity)
    }
    // using destructuring declarations
    for ((country, capitalCity) in capitalCityByCountry) {
        println("The capital city of $country is $capitalCity")
    }
}