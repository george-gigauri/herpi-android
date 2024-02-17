plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.gigauri.reptiledb.module.feature.main.presentation"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // In-App Update
    implementation("com.google.android.play:app-update-ktx:2.1.0")

    compose()
    hilt()
    hiltCompose()
}