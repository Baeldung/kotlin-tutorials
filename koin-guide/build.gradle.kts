plugins {
    kotlin("jvm") version "1.6.20"
    java
}

group = "com.baeldung.kotlin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val koin_version = "3.2.0-beta-1"
dependencies {
    implementation(kotlin("stdlib"))
    // Koin for Kotlin apps
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.ktor:ktor-server-core:1.6.8")
    implementation("io.ktor:ktor-server-netty:1.6.8")
    implementation("ch.qos.logback:logback-classic:1.2.5")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    // Testing
    testImplementation("io.insert-koin:koin-test:$koin_version")
    testImplementation("io.insert-koin:koin-test-junit5:$koin_version")
    testImplementation("io.mockk:mockk:1.12.3")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}