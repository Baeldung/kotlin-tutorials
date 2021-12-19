package com.baeldung.springboottestkotlin

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull
import org.junit.Assert.assertEquals;

class BankAccountServiceTest {

    val bankAccountRepository: BankAccountRepository = mockk();
    val bankAccountService = BankAccountService(bankAccountRepository);
    
    val bankAccount = BankAccount("ING", "123ING456", "JOHN SMITH");

    @Test
    fun whenGetBankAccount_thenReturnBankAccount() {
        //given
        every { bankAccountRepository.findByIdOrNull(1) } returns bankAccount;
    
        //when
        val result = bankAccountService.getBankAccount(1);
    
        //then
        verify(exactly = 1) { bankAccountRepository.findByIdOrNull(1) };
        assertEquals(bankAccount, result)
    }
}