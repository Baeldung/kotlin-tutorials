plugins {
    kotlin("jvm") version "1.8.22"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("mysql:mysql-connector-java:8+")

    testImplementation("io.kotest:kotest-runner-junit5:5.6.2")


    testImplementation("io.kotest.extensions:kotest-extensions-testcontainers:2.0.1")
    testImplementation("org.testcontainers:testcontainers:1.18.3")
    testImplementation("org.testcontainers:mysql:1.18.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}