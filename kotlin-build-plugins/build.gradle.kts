plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("kapt") version "2.0.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
}

repositories {
    mavenCentral()
}

buildscript {
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:10.2.0")
    }
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")
    testImplementation(kotlin("test"))
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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
