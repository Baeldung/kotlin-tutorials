package com.baeldung.createdirectory

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    println("Running Files.createDirectories")
    Files.createDirectories(Paths.get("./core-kotlin-modules/core-kotlin-5/src/main/kotlin/com/baeldung/createdirectory/createDirectories/inner"))
    println("Running Files.createDirectories 2nd time does nothing")
    Files.createDirectories(Paths.get("./core-kotlin-modules/core-kotlin-5/src/main/kotlin/com/baeldung/createdirectory/createDirectories/inner"))

    println("Running File(...).mkdirs")
    val resultMkdirs: Boolean = File("./core-kotlin-modules/core-kotlin-5/src/main/kotlin/com/baeldung/createdirectory/mkdirs/inner").mkdirs()
    println("Running File(...).mkdirs result: $resultMkdirs")

    println("Running File(...).mkdirs 2nd")
    val resultMkdirsRerun: Boolean = File("./core-kotlin-modules/core-kotlin-5/src/main/kotlin/com/baeldung/createdirectory/mkdirs/inner").mkdirs()
    println("Running File(...).mkdirs 2nd result: $resultMkdirsRerun")
}
