package com.baeldung.kotlin.jpa

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class PhoneNumber(
        @Column(nullable = false)
        val number: String,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?=null)