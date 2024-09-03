import java.util.*

plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("kapt") version "2.0.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
}

repositories {
    mavenCentral()
}

buildscript {
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:10.2.0")
    }
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")
    testImplementation(kotlin("test"))
}
tasks.withType<Test> {
    environment("CUSTOM_PROPERTY", project.findProperty("customProperty") as String)
    environment("API_URL", project.findProperty("apiUrl") as String)
}
tasks.register("generateCustomProperties") {
    val customPropertiesDir = file("$buildDir/generated/custom")
    val customPropertiesFile = file("$customPropertiesDir/custom.properties")

    // Mark the directory and file as outputs
    outputs.dir(customPropertiesDir)
    outputs.file(customPropertiesFile)

    doLast {
        customPropertiesDir.mkdirs()  // Ensure the directory exists
        val properties = Properties().apply {
            load(file("gradle.properties").inputStream())
        }
        customPropertiesFile.writer().use { writer ->
            properties.store(writer, "Generated Properties")
        }
    }
}

tasks.withType<Test> {
    dependsOn("generateCustomProperties")
    systemProperty("custom.properties.path", "$buildDir/generated/custom/custom.properties")
}