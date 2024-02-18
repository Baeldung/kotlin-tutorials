package com.baeldung.destructuringdeclarations

fun main() {

        //2.1. Objects
    val person = Person(1, "Jon Snow", 20)
    val(id, name, age) = person

    println(id)     //1
    println(name)   //Jon Snow
    println(age)    //20

    //2.2. Functions
    fun getPersonInfo() = Person(2, "Ned Stark", 45)
    val(_, _, _) = getPersonInfo()

    fun twoValuesReturn(): Pair<Int, String> {

        // needed code

        return Pair(1, "success")
    }

    // Now, to use this function:
    val (_, _) = twoValuesReturn()

    //2.3. Collections and For-loops
    var map: HashMap<Int, Person> = HashMap()
    map.put(1, person)

    for((key, value) in map){
        println("Key: $key, Value: $value")
    }

    //2.4. Underscore and Destructuring in Lambdas
    val (_, _, _) = person
    val (_, _) = person

    map.mapValues { entry -> "${entry.value}!" }
    map.mapValues { (_, value) -> "$value!" }

}