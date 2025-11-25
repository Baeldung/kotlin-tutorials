import com.expediagroup.graphql.plugin.gradle.config.GraphQLSerializer

description = "Example usage of Gradle plugin to generate GraphQL Kotlin Client"

val graphQLKotlinVersion = "7.0.1"
val ktorVersion = "2.3.11"
val logbackVersion = "1.4.14"
val kotlinTestUnit = "1.9.10"
val seleniumVersion = "4.16.1"
val swaggerVersion = "1.0.49"

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
    implementation("io.ktor", "ktor-server-status-pages", ktorVersion)
    implementation("io.ktor", "ktor-server-thymeleaf-jvm", ktorVersion)
    implementation("io.ktor", "ktor-client-auth", ktorVersion)
    implementation("io.ktor", "ktor-client-websockets", ktorVersion)
    implementation("io.ktor", "ktor-serialization-jackson", ktorVersion)
    implementation("io.ktor", "ktor-client-content-negotiation", ktorVersion)
    implementation("io.ktor", "ktor-server-cors", ktorVersion)
    implementation("io.ktor", "ktor-server-openapi", ktorVersion)
    implementation("io.ktor", "ktor-server-swagger", ktorVersion)
    implementation("io.ktor", "ktor-serialization-kotlinx-json", ktorVersion)
    
    implementation("io.swagger.codegen.v3", "swagger-codegen-generators", swaggerVersion)

    implementation("ch.qos.logback", "logback-classic", logbackVersion)

    implementation("io.ktor", "ktor-server-content-negotiation", ktorVersion)
    implementation("io.ktor", "ktor-serialization-gson", ktorVersion)
    implementation("io.ktor", "ktor-server-default-headers", ktorVersion)
    implementation("io.ktor", "ktor-server-call-logging", ktorVersion)

    testImplementation("io.ktor", "ktor-client-mock", ktorVersion)
    testImplementation("io.ktor", "ktor-server-tests", ktorVersion)

    testImplementation("org.jetbrains.kotlin", "kotlin-test-junit", kotlinTestUnit)
    testImplementation("org.seleniumhq.selenium", "selenium-java", seleniumVersion)
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

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

graphql {
    client {
        packageName = "com.baeldung.graphql.client.generated"
        schemaFile = file("src/main/resources/client/schema.graphql")
        serializer = GraphQLSerializer.JACKSON
    }
}

tasks.register<JavaExec>("runServer") {
    mainClass.set("APIServer")
    classpath = sourceSets["main"].runtimeClasspath
}
