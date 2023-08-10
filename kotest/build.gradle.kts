plugins {
    kotlin("jvm") version "1.8.22"
}

repositories {
    mavenCentral()
}

dependencies {
    val kotestVersion = "5.6.2"
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-framework-datatest:$kotestVersion")

    testImplementation("io.mockk:mockk:1.13.5")
    implementation("mysql:mysql-connector-java:8+")

    testImplementation("io.kotest:kotest-runner-junit5:5.6.2")

    testImplementation("io.kotest.extensions:kotest-extensions-testcontainers:2.0.1")
    testImplementation("org.testcontainers:testcontainers:1.18.3")
    testImplementation("org.testcontainers:mysql:1.18.3")

    testImplementation("io.kotest.extensions:kotest-extensions-mockserver:1.2.1")
    testImplementation("org.apache.httpcomponents:httpclient:4.5.14")
}

tasks.withType<Test> {
    useJUnitPlatform()
}