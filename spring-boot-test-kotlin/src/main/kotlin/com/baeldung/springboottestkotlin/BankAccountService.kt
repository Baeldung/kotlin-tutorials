package com.baeldung.springboottestkotlin

import java.util.Optional
import org.springframework.stereotype.Service
import org.springframework.data.repository.findByIdOrNull

@Service
class BankAccountService(var bankAccountRepository: BankAccountRepository) {
    
    fun addBankAccount(bankAccount: BankAccount): BankAccount {
        return bankAccountRepository.save(bankAccount);
    }
    
    fun getBankAccount(id: Long): BankAccount? {
        return bankAccountRepository.findByIdOrNull(id)
    }
    
}