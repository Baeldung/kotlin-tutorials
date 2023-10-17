import com.expediagroup.graphql.plugin.gradle.config.GraphQLSerializer

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
    implementation("com.expediagroup", "graphql-kotlin-client-jackson", expediaGroupVersion)

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
    id("com.expediagroup.graphql") version "7.0.1"
}

application {
    mainClass.set("com.baeldung.graphql.server.KtorApplicationKt")
}

kotlin {
    jvmToolchain(20)
}

graphql {
    client {
        packageName = "com.baeldung.graphql.client.generated"
        schemaFile = file("src/main/resources/schema.graphql")
        serializer = GraphQLSerializer.JACKSON
    }
}