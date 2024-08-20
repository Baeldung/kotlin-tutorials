group = "com.baeldung"
version = "1.0-SNAPSHOT"

val koinVersion = "4.0.0-RC1"
val koinAnnotationsVersion = "1.4.0-RC3"

repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "2.0.10"
    id("com.google.devtools.ksp") version "2.0.10-1.0.24"
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