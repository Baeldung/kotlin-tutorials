package com.baeldung.kotlin.jpa

import jakarta.persistence.*

@Entity
class PhoneNumber(
        @Column(nullable = false)
        val number: String,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?=null)