import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

fun getSecretKeys(): Properties {
    val keyFile = project.rootProject.file("local.properties")
    val secretKeys = Properties()
    secretKeys.load(FileInputStream(keyFile))
    return secretKeys
}

android {

    signingConfigs {
        create("release") {
            storeFile = file("keystore_file.jks")
            storePassword = getSecretKeys()["STORE_PASSWORD"] as? String
            keyPassword = getSecretKeys()["KEY_PASSWORD"] as? String
            keyAlias = getSecretKeys()["KEY_ALIAS"] as? String
        }
    }

    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }

    buildTypes {
        debug {
            versionNameSuffix = "-DEBUG"
            applicationIdSuffix = ".debug"
        }
        release {
            isDebuggable = false
            isMinifyEnabled = ProjectConfig.isMinifyEnabled
            signingConfig = signingConfigs.getByName("release")
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

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = false
        buildConfig = true
    }

    namespace = "com.gigauri.reptiledb"
}

dependencies {

    implementation(project(path = ":common"))
    implementation(project(path = ":core:data"))
    implementation(project(path = ":feature:home:data"))
    implementation(project(path = ":feature:reptileDetails:data"))
    implementation(project(path = ":feature:reptileDetails:presentation"))
    implementation(project(path = ":feature:search:data"))
    implementation(project(path = ":feature:main:presentation"))
    implementation(project(path = ":feature:team:data"))
    implementation(project(path = ":feature:faq:data"))
    implementation("androidx.hilt:hilt-work:1.2.0")

    workManager()

    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.12.0")

    // facebook
    implementation("com.facebook.android:facebook-android-sdk:18.0.3")
    implementation("com.facebook.android:audience-network-sdk:6.20.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.15.0"))

    // Dependency Injection
    implementation("com.google.dagger:hilt-android:2.56")
    ksp("com.google.dagger:hilt-compiler:2.56")
}