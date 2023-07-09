package com.baeldung.kotlin.jpa

import jakarta.persistence.*

@Entity
class Person @JvmOverloads constructor(
        @Column(nullable = false)
        val name: String,
        @Column(nullable = true)
        val email: String? = null,
        @OneToMany(cascade = [CascadeType.ALL])
        val phoneNumbers: List<PhoneNumber>?=null,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int?=null)
