package com.baeldung.springboottestkotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import java.util.Optional

@RestController
@RequestMapping("/api/bankAccount")
class BankController(var bankAccountService: BankAccountService) {

    @PostMapping
    fun addBankAccount(@RequestBody bankAccount:BankAccount) : ResponseEntity<BankAccount> {
        return ResponseEntity.ok(bankAccountService.addBankAccount(bankAccount))
    }

    @GetMapping
    fun getBankAccount(@RequestParam id:Long) : ResponseEntity<BankAccount> {
        var bankAccount: BankAccount? = bankAccountService.getBankAccount(id);
        if (bankAccount != null) {
            return ResponseEntity(bankAccount, HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    } 
}