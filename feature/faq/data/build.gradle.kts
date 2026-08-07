plugins {
    id("herpi.android.feature")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.gigauri.reptiledb.module.feature.faq.data"

    ksp {
        arg("room.schemaLocation", "$projectDir/schemas/faq")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
    }
}

dependencies {
    implementation(project(path = ":core:data"))
    implementation(project(path = ":core:domain"))
    implementation(project(path = ":feature:faq:domain"))

    retrofitWithGson()
    room()
    coroutines()
}
