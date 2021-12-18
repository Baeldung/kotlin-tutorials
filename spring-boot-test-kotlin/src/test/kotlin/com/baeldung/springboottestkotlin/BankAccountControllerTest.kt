package com.baeldung.springboottestkotlin

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.data.repository.findByIdOrNull
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

@WebMvcTest
class BankControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var bankAccountService: BankAccountService

    val bankAccount = BankAccount("ING", "123ING456", "JOHN SMITH");
    val mapper = jacksonObjectMapper();

    @Test
    fun `given bank exists when Get bankAccount then returns bank json with status 200`() {
        every { bankAccountService.getBankAccount(1) } returns bankAccount;

        mockMvc.perform(get("/api/bankAccount?id=1"))
        .andExpect(status().isOk)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.bankCode").value("ING"));
    }

    @Test
    fun `when bank doesn't exist when Get bankAccount then returns status 400`() {
    every { bankAccountService.getBankAccount(2) } returns null;

    mockMvc.perform(get("/api/bankAccount?id=2"))
    .andExpect(status().isBadRequest())
}

@Test
fun `when Add bank with bankAccount json then 200`() {
    every { bankAccountService.addBankAccount(bankAccount) } returns bankAccount;


    mockMvc.perform(post("/api/bankAccount").content(mapper.writeValueAsString(bankAccount)).contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk)
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    .andExpect(jsonPath("$.bankCode").value("ING"));
}
}

