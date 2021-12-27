import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    id("org.openapi.generator") version "5.3.0"
}

group = "com.baeldung"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("org.springdoc:springdoc-openapi-data-rest:1.6.3")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.3")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    testImplementation("com.squareup.moshi:moshi-adapters:1.13.0")
    testImplementation("com.squareup.okhttp3:okhttp:4.9.3")
}

val oasPackage = "com.baeldung.car"
val oasSpecLocation = "src/main/resources/car-spec.yaml"
val oasGenOutputDir = project.layout.buildDirectory.dir("generated-oas")

tasks.register("generateServer", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
    input = project.file(oasSpecLocation).path
    outputDir.set(oasGenOutputDir.get().toString())
    modelPackage.set("$oasPackage.model")
    apiPackage.set("$oasPackage.api")
    packageName.set(oasPackage)
    generatorName.set("kotlin-spring")
    configOptions.set(
      mapOf(
        "dateLibrary" to "java8",
        "interfaceOnly" to "true",
        "useTags" to "true"
      )
    )
}

val clientOutput = project.layout.buildDirectory.dir("generated-oas-test")

tasks.register("generateClient", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
    input = project.file(oasSpecLocation).path
    outputDir.set(clientOutput.get().toString())
    modelPackage.set("$oasPackage.client.model")
    apiPackage.set("$oasPackage.client.api")
    packageName.set(oasPackage)
    generatorName.set("kotlin")
    configOptions.set(
      mapOf(
        "dateLibrary" to "java8",
        "useTags" to "true"
      )
    )
}

sourceSets {
    val main by getting
    main.java.srcDir("${oasGenOutputDir.get()}/src/main/kotlin")
    val test by getting
    test.java.srcDir("${clientOutput.get()}/src/main/kotlin")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
    dependsOn("generateServer")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
