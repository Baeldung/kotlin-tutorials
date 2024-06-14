plugins {
    kotlin("multiplatform").version("1.8.21").apply(true)
    id("com.android.library").version("8.0.2").apply(true)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
        }
    }
}

android {
    namespace = "com.baeldung.mobile_multiplatform"
    compileSdkVersion = "android-31"
    defaultConfig {
        minSdk = 26
    }
}

repositories {
    mavenCentral()
    //jcenter()
    google()
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlinx")
    }
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    }
}