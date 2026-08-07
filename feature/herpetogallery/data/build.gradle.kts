plugins {
    id("herpi.android.feature")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ge.gigauri.herpi.feature.herpetogallery.data"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":feature:herpetogallery:domain"))

    pagingCommon()
    retrofitWithGson()
    coroutines()
}
