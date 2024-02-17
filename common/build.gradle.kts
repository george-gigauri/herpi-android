import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

fun getSecretKeys(): Properties {
    val keyFile = project.rootProject.file("local.properties")
    val secretKeys = Properties()
    secretKeys.load(FileInputStream(keyFile))
    return secretKeys
}

android {
    namespace = "com.gigauri.reptiledb.module.common"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        // Fields
        val mapsApiKey = getSecretKeys()["MAPS_API_KEY"]
        val fbClientToken = getSecretKeys()["FACEBOOK_CLIENT_ID"]
        val fbAppId = getSecretKeys()["FACEBOOK_APP_ID"]

        // Build Config Fields
        buildConfigField("String", "MAPS_API_KEY", "\"${mapsApiKey}\"")
        buildConfigField("String", "FACEBOOK_CLIENT_ID", "\"${fbClientToken}\"")
        buildConfigField("String", "FACEBOOK_APP_ID", "\"${fbAppId}\"")

        // Res Value Fields
        resValue("string", "MAPS_API_KEY", "\"${mapsApiKey}\"")
        resValue("string", "FACEBOOK_CLIENT_ID", "\"${fbClientToken}\"")
        resValue("string", "FACEBOOK_APP_ID", "\"${fbAppId}\"")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.browser:browser:1.7.0")

    api("com.google.android.gms:play-services-location:21.1.0")

    // Gson
    api("com.google.code.gson:gson:2.10.1")

    // Dependency Injection
    hilt()
}