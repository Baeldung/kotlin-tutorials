plugins {
    kotlin("jvm") version "1.8.21"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
    id "org.jlleitschuh.gradle.ktlint" version "7.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")
    classpath ("org.jlleitschuh.gradle:ktlint-gradle:7.1.0")
}
