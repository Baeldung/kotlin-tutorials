package com.baeldung.iteratePropertiesOfDataClass

fun getFields(person: Person): List<String> {
    var list = mutableListOf<String>()
    val cls = person
    for(field in cls) {
        list.add(field.toString())
    }

    return list
}
