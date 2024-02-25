package com.baeldung.aes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EncryptionDecryptionUnitTest {
    @Test
    fun `Given text when encrypted and decrypted should return original text`() {
        val originalText = "Hello Kotlin AES Encryption!"
        val secretKey = generateAESKey(256)

        val encryptedData = aesEncrypt(originalText.toByteArray(), secretKey)
        val decryptedData = aesDecrypt(encryptedData, secretKey)
        val decryptedText = String(decryptedData)

        assertEquals(originalText, decryptedText, "The decrypted text does not match the original")
    }
}