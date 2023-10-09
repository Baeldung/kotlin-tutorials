description = "Example usage of Gradle plugin to generate GraphQL Kotlin Client"

val expediaGroupVersion = "7.0.1"
val ktorVersion = "2.3.4"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.expediagroup", "graphql-kotlin-ktor-client", expediaGroupVersion)
    implementation("com.expediagroup", "graphql-kotlin-schema-generator", expediaGroupVersion)
    implementation("io.ktor", "ktor-server-core", ktorVersion)
    implementation("io.ktor", "ktor-server-cio", ktorVersion)
}

plugins {
    application
    kotlin("jvm") version "1.9.10"
    id("io.ktor.plugin") version "2.3.4"
}

application {
    mainClass.set("com.baeldung.graphql.KtorApplicationKt")
}
