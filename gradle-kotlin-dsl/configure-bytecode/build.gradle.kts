import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.0"
}

group = "com.baeldung"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

// compileJava, compileKotlin, compileKotlinTest, compileJavaTest
java {
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

// for test
tasks.compileTestJava {
    targetCompatibility = "11"
}

tasks.compileTestKotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

// configureEach
tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

// toolChain
kotlin {
    jvmToolchain(11)
}