plugins {
    java
    application
    kotlin("jvm") version "1.8.22"
}

sourceSets {
    create("analytics") {
        java.srcDir("src/analytics/java")
        java.srcDir("src/analytics/kotlin")
        resources.srcDir("src/analytics/resources")
    }
}

dependencies {
    // "analyticsImplementation"("org.apache.commons:commons-lang3:3.12.0")
    // /\ \/ are equal
    sourceSets.named("analytics") {
        implementation("org.apache.commons:commons-lang3:3.12.0")
    }


    implementation(sourceSets.named("analytics").get().output)
}

application {
    mainClass.set("com.baeldung.SampleKt")
    applicationDefaultJvmArgs = listOf("-Dconfig.file=analytics.conf")
}