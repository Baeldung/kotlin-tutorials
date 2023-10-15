description = "Example usage of Gradle plugin to generate GraphQL Kotlin Client"

val expediaGroupVersion = "7.0.1"
val ktorVersion = "2.3.5"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.expediagroup", "graphql-kotlin-ktor-client", expediaGroupVersion)
    implementation("com.expediagroup", "graphql-kotlin-ktor-server", expediaGroupVersion)
    implementation("com.expediagroup", "graphql-kotlin-schema-generator", expediaGroupVersion)
    implementation("io.ktor", "ktor-server-core", ktorVersion)
    implementation("io.ktor", "ktor-server-netty", ktorVersion)
    implementation("io.ktor", "ktor-server-websockets", ktorVersion)
    testImplementation("io.ktor", "ktor-server-tests", ktorVersion)
    testImplementation("io.ktor", "ktor-client-content-negotiation", ktorVersion)
    testImplementation("io.ktor", "ktor-serialization-kotlinx-json", ktorVersion)
    testImplementation("org.jetbrains.kotlin", "kotlin-test-junit", "1.9.10")
}

plugins {
    kotlin("jvm") version "1.9.10"
    id("io.ktor.plugin") version "2.3.5"
}

application {
    mainClass.set("com.baeldung.graphql.server.KtorApplicationKt")
}

kotlin {
    jvmToolchain(20)
}