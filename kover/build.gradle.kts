import kotlinx.kover.gradle.plugin.dsl.AggregationType
import kotlinx.kover.gradle.plugin.dsl.MetricType

plugins {
    kotlin("jvm") version "1.9.20"
    id("org.jetbrains.kotlinx.kover") version "0.7.5"
}

repositories {
    mavenCentral()
}

dependencies {

    testImplementation("junit:junit:4.13.1")
}

koverReport {
    filters {
        excludes {
            classes("com.baeldung.code.not.covered.NotCoveredClass") // Exclude specific classes
            packages("com.baeldung.code.not.covered.*") // Exclude a whole package
            annotatedBy("com.baeldung.annotations.ExcludeFromCoverage") // Exclude classes or functions with a specific annotation
        }
        includes {
            classes("com.baeldung.code.covered.CoveredClass") // Include specific classes
        }
        excludes {
            classes("com.baeldung.code.not.covered")
        }
        verify {
            rule("Basic Line Coverage") {
                isEnabled = true
                bound {
                    minValue = 80 // Minimum coverage percentage
                    maxValue = 100 // Maximum coverage percentage (optional)
                    metric = MetricType.LINE
                    aggregation = AggregationType.COVERED_PERCENTAGE
                }
            }

            rule("Branch Coverage") {
                isEnabled = true
                bound {
                    minValue = 70 // Minimum coverage percentage for branches
                    metric = MetricType.BRANCH
                }
            }
        }

        verify {
            rule {
                isEnabled = true
                bound {
                    minValue = 80 // Minimum coverage percentage
                }
            }
        }
    }
}