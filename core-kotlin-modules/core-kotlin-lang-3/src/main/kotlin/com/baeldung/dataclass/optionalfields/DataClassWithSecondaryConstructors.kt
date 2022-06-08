package com.baeldung.dataclass.optionalfields

class DataClassWithSecondaryConstructors(
    val name: String,
    val surname: String,
    val age: Number
) {
    constructor() : this("", "Doe", Int.MIN_VALUE)
    constructor(name: String) : this(name, "Deere", Int.MIN_VALUE)
    constructor(name: String, surname: String) : this(name, surname, Int.MIN_VALUE)
}