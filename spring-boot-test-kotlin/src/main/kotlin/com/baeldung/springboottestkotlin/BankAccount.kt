package com.baeldung.springboottestkotlin

import org.springframework.stereotype.Component
import javax.persistence.GeneratedValue
import javax.persistence.Entity
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class BankAccount (
    var bankCode:String,
    var accountNumber:String,
    var accountHolderName:String,
    @Id @GeneratedValue var id: Long? = null
)
