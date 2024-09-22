plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "gradle-kotlin-dsl"

include("gradle-kotlin-dsl", "custom-source-set")
include("configure-bytecode")
