package com.baeldung.files

import org.junit.Test
import java.io.File

class ListingFilesRecursivelyUnitTest {

    @Test
    fun `Walk - Top Down as default`() {
        File("src/test/resources").walk().forEach {
            println(it)
        }
    }

    @Test
    fun `Walk - Bottom Up`() {
        File("src/test/resources").walk(FileWalkDirection.BOTTOM_UP).forEach {
            println(it)
        }
    }

    @Test
    fun `Walk - shorthands`() {
        println("Walk Top Down")
        File("src/test/resources").walkTopDown().forEach {
            println(it)
        }

        println("Walk Bottom Up")
        File("src/test/resources").walkBottomUp().forEach {
            println(it)
        }
    }

}
