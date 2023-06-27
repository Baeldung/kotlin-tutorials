plugins {
    kotlin("jvm")
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.springframework.boot") version "3.0.1"

}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("io.kotest:kotest-runner-junit5:5.6.2")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")

}

tasks.withType<Test> {
    useJUnitPlatform()
}