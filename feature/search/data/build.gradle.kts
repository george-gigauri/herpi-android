plugins {
    id("herpi.android.feature")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.gigauri.reptiledb.module.feature.search.data"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":feature:search:domain"))
    implementation(project(":core:data"))

    pagingCommon()
    retrofit()
    coroutines()
}
