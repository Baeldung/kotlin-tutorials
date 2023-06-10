import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.io.OutputStream

plugins {
    `java-library`
    id("org.flywaydb.flyway") version "8.0.2"
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.springframework.org/release")
    }
}

sourceSets {
    create("integrationTest") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

val integrationTestImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.implementation.get())
    extendsFrom(configurations.testImplementation.get())
}

val integrationTestRuntimeOnly: Configuration by configurations.getting {
    extendsFrom(configurations.implementation.get())
    extendsFrom(configurations.testRuntimeOnly.get())
}

val integrationTest = task<Test>("integrationTest") {
    description = "Task to run integration tests"
    group = "verification"

    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
    shouldRunAfter("test")
}

tasks.check { dependsOn(integrationTest) }

dependencies {
    api("com.google.inject:guice:5.0.1")
    implementation("com.google.guava:guava:31.0-jre")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testImplementation("org.mockito:mockito-core:4.0.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.register("helloUserCmd") {
    val user: String? = System.getenv("USER")
    project.exec {
        commandLine("echo", "Hello,", "$user!")
    }
}

tasks.register("helloUserInVarCmd") {
    val outputStream = ByteArrayOutputStream()
    val user: String? = System.getenv("USER")
    project.exec {
        standardOutput = outputStream
        commandLine("echo", "Hello,", "$user!")
    }
    val output = outputStream.toString().trim()
    println("Command output: $output")
}

tasks.register("tmpFilesCmd") {
    val outputFile = File("/tmp/output.txt")
    val outputStream: OutputStream = FileOutputStream(outputFile)
    project.exec {
        standardOutput = outputStream
        workingDir = project.file("/tmp")
        commandLine("ls", workingDir)
    }
}

tasks.register("alwaysFailCmd") {
    val result = project.exec {
        commandLine("ls", "invalid_path")
        isIgnoreExitValue = true
    }
    if (result.exitValue == 0) {
        println("Command executed successfully.")
    } else {
        println("Command execution failed.")
        println("Command status: $result")
    }
}