package com.baeldung.springboottestkotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class BankAccountRepositoryUnitTest {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var bankAccountRepository: BankAccountRepository

    @Test
    fun WhenFindById_thenReturnBankAccount() {
        val ingBankAccount = BankAccount("ING", "123ING456", "JOHN SMITH");
        entityManager.persist(ingBankAccount)
        entityManager.flush()
        val ingBankAccountFound = bankAccountRepository.findByIdOrNull(ingBankAccount.id!!)
        assertThat(ingBankAccountFound == ingBankAccount)
    }

}