plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    application
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")


}

application {

    mainClass.set("com.baeldung.kotlin.kafka.AppKt")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
