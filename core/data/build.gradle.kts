plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.gigauri.reptiledb.module.core.data"
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

    kotlinOptions {
        jvmTarget = "1.8"
    }

    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

dependencies {

    implementation(project(path = ":core"))
    implementation(project(path = ":core:domain"))

    api("androidx.core:core-ktx:1.16.0")
    api("androidx.appcompat:appcompat:1.7.1")
    api("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    // Firebase
    api(platform("com.google.firebase:firebase-bom:33.15.0"))
    api("com.google.firebase:firebase-crashlytics-ktx")
    api("com.google.firebase:firebase-analytics-ktx")
    api("com.google.firebase:firebase-messaging-ktx")
    api("com.google.firebase:firebase-inappmessaging-display-ktx")
    api("com.google.firebase:firebase-dynamic-links-ktx")

    // Facebook
    api("com.facebook.android:facebook-android-sdk:18.0.3")
    api("com.facebook.android:audience-network-sdk:6.20.0")

    // Paging
    api("androidx.paging:paging-runtime-ktx:3.3.6")
    api("androidx.room:room-paging:2.7.1")

    // Room
    api("androidx.room:room-ktx:2.7.1")
    api("androidx.room:room-runtime:2.7.1")
    ksp("androidx.room:room-compiler:2.7.1")

    // Network
    api("com.squareup.retrofit2:retrofit:2.11.0")
    api("com.google.code.gson:gson:2.11.0")
    api("com.squareup.retrofit2:converter-gson:2.11.0")

    // Database
    api("androidx.datastore:datastore:1.1.7")
    api("androidx.datastore:datastore-preferences:1.1.7")

    // Dependency Injection
    api("com.google.dagger:hilt-android:2.56")
    ksp("com.google.dagger:hilt-compiler:2.56")

    // Debug Api Calls
    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")
}