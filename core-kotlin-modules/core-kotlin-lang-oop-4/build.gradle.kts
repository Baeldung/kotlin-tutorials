plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("kapt") version "1.8.21"
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

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
//    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    compileOnly("com.github.LunarWatcher:KClassUnpacker:v1.0.1")
    testImplementation(kotlin("test"))
}

kapt {
    dependencies {
        kapt("com.github.LunarWatcher:KClassUnpacker:v1.0.1")
    }
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}