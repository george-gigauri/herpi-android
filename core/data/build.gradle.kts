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

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
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
}

dependencies {

    implementation(project(path = ":core"))
    implementation(project(path = ":core:domain"))

    api("androidx.core:core-ktx:1.12.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Firebase
    api(platform("com.google.firebase:firebase-bom:32.7.2"))
    api("com.google.firebase:firebase-crashlytics-ktx")
    api("com.google.firebase:firebase-analytics-ktx")
    api("com.google.firebase:firebase-messaging-ktx")
    api("com.google.firebase:firebase-inappmessaging-display-ktx")
    api("com.google.firebase:firebase-dynamic-links-ktx")

    // Facebook
    api("com.facebook.android:facebook-android-sdk:15.0.2")
    api("com.facebook.android:audience-network-sdk:6.14.0")

    // Paging
    api("androidx.paging:paging-runtime-ktx:3.2.1")
    api("androidx.room:room-paging:2.6.1")

    // Room
    api("androidx.room:room-ktx:2.6.1")
    api("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    // Network
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.google.code.gson:gson:2.10.1")
    api("com.squareup.retrofit2:converter-gson:2.9.0")

    // Database
    api("androidx.datastore:datastore:1.0.0")
    api("androidx.datastore:datastore-preferences:1.0.0")

    // Dependency Injection
    api("com.google.dagger:hilt-android:2.50")
    ksp("com.google.dagger:hilt-compiler:2.50")

    // Debug Api Calls
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.0")
}