plugins {
    id("java")
    kotlin("jvm") version "1.9.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Test> {
    environment("CUSTOM_PROPERTY", project.findProperty("custom.property") as String)
    environment("API_URL", project.findProperty("api.url") as String)
}

tasks.register<Copy>("generateCustomProperties") {
    from("${project.rootDir}/gradle.properties")
    into("src/generated/resources")
    rename { "custom.properties" }
}

tasks.named<Copy>("processResources") {
    dependsOn("generateCustomProperties")
    from("src/generated/resources") {
        include("custom.properties")
    }
}
kotlin {
    jvmToolchain(19)
}