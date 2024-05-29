plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("kapt") version "1.8.21"
    id("org.jlleitschuh.gradle.ktlint") version "7.1.0"
}

repositories {
    mavenCentral()
}

buildscript {
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:7.1.0")
    }
}

dependencies {
    testImplementation(kotlin("test"))
}