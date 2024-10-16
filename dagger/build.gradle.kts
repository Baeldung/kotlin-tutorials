plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("kapt") version "2.0.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.dagger:dagger:2.52")
    kapt("com.google.dagger:dagger-compiler:2.52")
    kaptTest("com.google.dagger:dagger-compiler:2.52")
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}
tasks.test {
    useJUnitPlatform()
}