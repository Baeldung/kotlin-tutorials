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
    environment("CUSTOM_PROPERTY", project.findProperty("custom.property") as String)
    environment("API_URL", project.findProperty("api.url") as String)
}
tasks.register("generateCustomProperties") {
    val customPropertiesDir = file("src/generated/resources")
    val customPropertiesFile = file("$customPropertiesDir/custom.properties")

    outputs.dir(customPropertiesDir)
    outputs.file(customPropertiesFile)

    doLast {
        customPropertiesDir.mkdirs()
        val properties = Properties().apply {
            load(file("gradle.properties").inputStream())
        }
        customPropertiesFile.writer().use { writer ->
            properties.store(writer, "Generated Properties")
        }
    }
}

tasks.named<Copy>("processResources") {
    dependsOn("generateCustomProperties")
    from("src/generated/resources") {
        include("custom.properties")
    }
}