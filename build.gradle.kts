import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    id("org.openapi.generator") version "6.2.0"
}

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation("com.google.code.gson:gson:2.9.1")
}

openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set("spec.json")
    outputDir.set(file(".").toString())

    with(globalProperties) {
        put("modelDocs", "false")
        put("apis", "false")
        put("models", "")
    }

    with(configOptions) {
        put("serializationLibrary", "gson")
    }
}

tasks.withType(org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class.java) {
    outputs.upToDateWhen { false }
    outputs.cacheIf { false }
}