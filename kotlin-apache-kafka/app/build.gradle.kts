plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    application
}

repositories {
    mavenLocal()
    mavenCentral()
}

val testcontainersVersion = "1.17.6"
val kafkaApiVersion = "3.3.1"
dependencies {
    implementation("org.apache.kafka:kafka-clients:$kafkaApiVersion")

    implementation("org.slf4j:slf4j-simple:2.0.5")
    implementation("org.apache.kafka:kafka-streams:$kafkaApiVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    testImplementation("org.apache.kafka:kafka-streams-test-utils:$kafkaApiVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.testcontainers:testcontainers:$testcontainersVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testcontainersVersion")
    testImplementation("org.testcontainers:kafka:$testcontainersVersion")
}

application {

    mainClass.set("com.baeldung.kotlin.kafka.AppKt")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
