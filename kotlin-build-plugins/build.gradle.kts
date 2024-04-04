plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("kapt") version "1.8.21"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
    id("org.jlleitschuh.gradle.ktlint") version "7.1.0"
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

buildscript {
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:7.1.0")
    }
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")
    compileOnly("com.github.LunarWatcher:KClassUnpacker:v1.0.1")
    testImplementation(kotlin("test"))
}

kapt {
    dependencies {
        kapt("com.github.LunarWatcher:KClassUnpacker:v1.0.1")
    }
}
