import com.expediagroup.graphql.plugin.gradle.config.GraphQLSerializer

description = "Example usage of Gradle plugin to generate GraphQL Kotlin Client"

val graphQLKotlinVersion = "7.0.1"
val ktorVersion = "2.3.5"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.expediagroup", "graphql-kotlin-ktor-client", graphQLKotlinVersion)
    implementation("com.expediagroup", "graphql-kotlin-ktor-server", graphQLKotlinVersion)
    implementation("com.expediagroup", "graphql-kotlin-client-jackson", graphQLKotlinVersion)

    implementation("io.ktor", "ktor-client-core", ktorVersion)
    implementation("io.ktor", "ktor-server-core", ktorVersion)
    implementation("io.ktor", "ktor-server-netty", ktorVersion)
    implementation("io.ktor", "ktor-server-websockets", ktorVersion)
    implementation("io.ktor", "ktor-client-auth", ktorVersion)
    implementation("io.ktor", "ktor-client-websockets", ktorVersion)
    implementation("io.ktor", "ktor-serialization-jackson", ktorVersion)
    implementation("io.ktor", "ktor-client-content-negotiation", ktorVersion)


    testImplementation("io.ktor", "ktor-client-mock", ktorVersion)
    testImplementation("io.ktor", "ktor-server-tests", ktorVersion)
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

//There seems to be a bug here with the graphqlGenerateSDL task where if the Subscription uses the Flow class, it will throw an error
//graphql {
//    schema {
//        packages = listOf("com.baeldung.graphql.server")
//    }
//}

kotlin {
    jvmToolchain(20)
}

graphql {
    client {
        packageName = "com.baeldung.graphql.client.generated"
        schemaFile = file("src/main/resources/client/schema.graphql")
        serializer = GraphQLSerializer.JACKSON
    }
}