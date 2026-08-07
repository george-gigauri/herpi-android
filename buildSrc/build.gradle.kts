plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:9.3.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.10")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.56")
    implementation("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:2.3.6")
}
