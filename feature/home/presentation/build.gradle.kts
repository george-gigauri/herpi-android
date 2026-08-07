plugins {
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.10"
    id("herpi.android.feature.compose")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.gigauri.reptiledb.module.feature.home"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
    }
}

dependencies {
    implementation(project(path = ":common"))
    implementation(project(path = ":core:domain"))
    implementation(project(path = ":core:presentation"))
    implementation(project(path = ":feature:home:domain"))
    implementation(project(path = ":feature:herpetogallery:presentation"))

    coil()
    shimmer()
    permissions()

    // Maps
    implementation("org.osmdroid:osmdroid-android:6.1.18")
}
