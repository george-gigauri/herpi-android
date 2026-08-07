plugins {
    id("org.jetbrains.kotlin.plugin.compose") version "2.4.10"
    id("herpi.android.feature.compose")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.gigauri.reptiledb.module.feature.reptileDetails.presentation"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":core:presentation"))
    implementation(project(":core:domain"))
    implementation(project(":feature:reptileDetails:domain"))
    implementation(project(":imageViewer"))

    // Maps
    implementation("org.osmdroid:osmdroid-android:6.1.18")

    coil()
}
