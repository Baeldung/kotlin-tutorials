plugins {
    kotlin("jvm") version "1.8.21"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
}

repositories {
    mavenCentral()
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")
}
