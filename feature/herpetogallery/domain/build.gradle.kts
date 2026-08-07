plugins {
    id("herpi.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ge.gigauri.herpi.feature.herpetogallery.domain"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
    }
}

dependencies {
    implementation(project(":core:domain"))

    inject()
    pagingCommon()
}
