group = "com.baeldung"
version = "1.0-SNAPSHOT"

val koinVersion = "3.5.6"
val koinAnnotationsVersion = "1.3.1"

repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "1.9.24"
    id("com.google.devtools.ksp") version "1.9.24-1.0.20"
}

dependencies {
    implementation("io.insert-koin:koin-core-jvm:$koinVersion")
    implementation("io.insert-koin:koin-annotations:$koinAnnotationsVersion")
    ksp("io.insert-koin:koin-ksp-compiler:$koinAnnotationsVersion")
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}