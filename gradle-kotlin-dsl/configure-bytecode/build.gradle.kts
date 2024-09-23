import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.0.0"
}

//repositories {
//    mavenCentral()
//}
//
//dependencies {
//    testImplementation("org.jetbrains.kotlin:kotlin-test")
//}
//
//tasks.test {
//    useJUnitPlatform()
//}

java {
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}