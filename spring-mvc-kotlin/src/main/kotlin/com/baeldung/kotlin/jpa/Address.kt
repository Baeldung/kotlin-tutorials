package com.baeldung.kotlin.jpa

import javax.persistence.*

@Entity
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val name: String,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val phoneNumbers: List<PhoneNumber>
)