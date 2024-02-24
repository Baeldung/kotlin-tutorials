package com.baeldung.renameFile

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

class RenameFileUnitTest {

    @Test
    fun `when using renameTo, then file is renamed`() {
        val oldFilePath = Paths.get("src/test/resources/oldFile.txt")
        val newFilePath = Paths.get("src/test/resources/newFile.txt")

        val oldFile = oldFilePath.toFile()
        oldFile.writeText("Hello Kotlin")

        val newFile = newFilePath.toFile()

        if (oldFile.renameTo(newFile)) {
            assertThat(newFile.exists()).isTrue()
        } else {
            fail("Failed to rename file.")
        }
        //cleanup
        newFile.delete()
    }

@Test
fun `when renaming, checks on files succeed`() {
    val oldFilePath = Paths.get("src/test/resources/oldFile.txt")
    val newFilePath = Paths.get("src/test/resources/newFile.txt")

    val oldFile = oldFilePath.toFile()
    oldFile.writeText("Hello Kotlin")

    val newFile = newFilePath.toFile()

    if (oldFile.exists()) {
        if (newFile.exists()) {
            fail("A file with the new name already exists.")
        } else {
            if (oldFile.renameTo(newFile)) {
                assertThat(newFile.exists()).isTrue()
            } else {
                fail("Fail rename failed")
            }
        }
    } else {
        fail("The old file does not exist.")
    }
    //cleanup
    newFile.delete()
}

    @Test
    fun `when using move, then file is renamed`() {
        val oldFilePath = Paths.get("src/test/resources/oldFile.txt")
        val newFilePath = Paths.get("src/test/resources/newFile.txt")

        val oldFile = oldFilePath.toFile()
        oldFile.writeText("Hello Kotlin")

        Files.move(oldFilePath, newFilePath, StandardCopyOption.REPLACE_EXISTING)
        val newFile = newFilePath.toFile()
        assertThat(newFile.exists()).isTrue()
        //cleanup
        newFile.delete()
    }

}