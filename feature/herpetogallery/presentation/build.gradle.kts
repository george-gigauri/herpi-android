plugins {
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.10"
    id("herpi.android.feature.compose")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ge.gigauri.herpi.feature.herpetogallery.presentation"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":core:domain"))
    implementation(project(":core:presentation"))
    implementation(project(":feature:herpetogallery:domain"))

    coil()
    pagingRuntimeCompose()
}
