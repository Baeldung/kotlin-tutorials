plugins {
    kotlin("jvm") version "2.0.20"
}

group = "com.baeldung"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://packages.jetbrains.team/maven/p/kds/kotlin-ds-maven")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kandy-lets-plot:0.7.1")
    implementation("org.jetbrains.kotlinx:kotlin-statistics-jvm:0.3.1")
    testImplementation("org.assertj:assertj-core:3.6.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}