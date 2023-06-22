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
}

tasks.withType<Test> {
    useJUnitPlatform()
}