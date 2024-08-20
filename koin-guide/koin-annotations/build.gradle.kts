group = "com.baeldung"
version = "1.0-SNAPSHOT"

val koinVersion = "3.5.6"
val koinAnnotationsVersion = "1.3.1"

repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "2.0.10"
    id("com.google.devtools.ksp") version "2.0.10-1.0.24"
}

dependencies {
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-annotations:$koinAnnotationsVersion")
    ksp("io.insert-koin:koin-ksp-compiler:$koinAnnotationsVersion")
}

sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}