package com.baeldung.autowired

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController(
    val personService: PersonService,
    val addressService: AddressService) {

    @GetMapping
    fun person(): String {
        return "Hello ${personService.person.name}, Address: ${personService.address.fullAddress}"
    }
}

@Service
class PersonService {
    var person: Person
    lateinit var address: Address

    constructor(person: Person) {
        this.person = person
    }

    @Autowired
    constructor(person: Person, address: Address): this(person) {
        this.address = address
    }
}

@Service
class AddressService @Autowired constructor(val address: Address)

@Component
class Address(val fullAddress: String = "101 st, abc city, xyz state 12345")

@Component
class Person(val name: String = "John Doe")