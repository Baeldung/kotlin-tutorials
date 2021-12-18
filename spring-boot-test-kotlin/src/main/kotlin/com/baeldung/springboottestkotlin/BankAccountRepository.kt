package com.baeldung.springboottestkotlin

import org.springframework.data.repository.CrudRepository
import java.util.Optional
import org.springframework.stereotype.Repository

@Repository
interface BankAccountRepository : CrudRepository<BankAccount, Long> {
    
}
