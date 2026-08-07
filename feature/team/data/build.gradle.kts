plugins {
    id("herpi.android.feature")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.gigauri.reptiledb.module.feature.team.data"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":feature:team:domain"))

    retrofitWithGson()
    coroutines()
}
