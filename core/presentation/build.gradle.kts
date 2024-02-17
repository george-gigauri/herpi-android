plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.gigauri.reptiledb.module.core.presentation"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = ProjectConfig.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":core:domain"))
    implementation(project(":feature:reptileDetails:domain"))

    api("androidx.core:core-ktx:1.12.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Compose
    api("androidx.compose.runtime:runtime:${Versions.compose}")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}")
    api("androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}")
    api("androidx.activity:activity-compose:${Versions.activityCompose}")
    api("androidx.compose.ui:ui:${Versions.compose}")
    api("androidx.compose.ui:ui-tooling-preview:${Versions.compose}")
    api("androidx.compose.material3:material3:${Versions.material3}")
    api("androidx.navigation:navigation-compose:${Versions.navigation}")
    api("com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}")

    // Hilt
    api("com.google.dagger:hilt-android:${Versions.hilt}")
    kapt("com.google.dagger:hilt-compiler:${Versions.hilt}")

}