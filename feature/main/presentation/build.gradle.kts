plugins {
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.10"
    id("herpi.android.feature.compose")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.gigauri.reptiledb.module.feature.main.presentation"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(path = ":core:domain"))
    implementation(project(path = ":core:presentation"))
    implementation(project(path = ":feature:home:domain"))
    implementation(project(path = ":feature:home:presentation"))
    implementation(project(path = ":feature:reptileDetails:domain"))
    implementation(project(path = ":feature:reptileDetails:presentation"))
    implementation(project(path = ":feature:search:presentation"))
    implementation(project(path = ":feature:team:domain"))
    implementation(project(path = ":feature:team:presentation"))
    implementation(project(path = ":feature:faq:domain"))
    implementation(project(path = ":feature:faq:presentation"))
    implementation(project(":feature:herpetogallery:presentation"))

    // In-App Update
    implementation("com.google.android.play:app-update-ktx:2.1.0")

    implementation("org.osmdroid:osmdroid-android:6.1.18")
}
