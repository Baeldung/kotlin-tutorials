package com.baeldung.springboottestkotlin

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class BankAccount (
    var bankCode:String,
    var accountNumber:String,
    var accountHolderName:String,
    @Id @GeneratedValue var id: Long? = null
)
