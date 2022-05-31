package com.baeldung.kotlin.jpa

import javax.persistence.*

@Entity
data class Address(
    val name: String,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val phoneNumbers: List<PhoneNumber>,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null)