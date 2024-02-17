import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.shimmer() {
    implementation("com.valentinilk.shimmer:compose-shimmer:${Versions.shimmer}")
}

fun DependencyHandler.permissions() {
    implementation("com.google.accompanist:accompanist-permissions:${Versions.accompanist}")
}

fun DependencyHandler.compose() {
    implementation("androidx.compose.runtime:runtime:${Versions.compose}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}")
    implementation("androidx.activity:activity-compose:${Versions.activityCompose}")
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    debugImplementation("androidx.compose.ui:ui-tooling-preview:${Versions.compose}")
    implementation("androidx.compose.material3:material3:${Versions.material3}")
    implementation("androidx.navigation:navigation-compose:${Versions.navigation}")
    implementation("com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
}

fun DependencyHandler.hiltCompose() {
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    ksp("androidx.hilt:hilt-compiler:1.1.0")
}

fun DependencyHandler.hilt() {
    implementation("com.google.dagger:hilt-android:${Versions.hilt}")
    ksp("com.google.dagger:hilt-compiler:${Versions.hilt}")
}

fun DependencyHandler.inject() {
    implementation("javax.inject:javax.inject:1")
}

fun DependencyHandler.retrofit() {
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
}

fun DependencyHandler.retrofitWithGson() {
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.google.code.gson:gson:${Versions.gson}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit}")
}

fun DependencyHandler.room() {
    implementation("androidx.room:room-ktx:${Versions.room}")
    implementation("androidx.room:room-runtime:${Versions.room}")
    ksp("androidx.room:room-compiler:${Versions.room}")
}

fun DependencyHandler.dataStore() {
    implementation("androidx.datastore:datastore:${Versions.dataStore}")
    implementation("androidx.datastore:datastore-preferences:${Versions.dataStore}")
}

fun DependencyHandler.roomPaging() {
    implementation("androidx.room:room-ktx:${Versions.room}")
    implementation("androidx.room:room-runtime:${Versions.room}")
    implementation("androidx.room:room-paging:${Versions.room}")
    ksp("androidx.room:room-compiler:${Versions.room}")
}

fun DependencyHandler.pagingCommon() {
    implementation("androidx.paging:paging-common-ktx:${Versions.paging}")
}

fun DependencyHandler.pagingRuntimeCompose() {
    implementation("androidx.paging:paging-runtime-ktx:${Versions.paging}")
    implementation("androidx.paging:paging-compose:${Versions.paging}")
}

fun DependencyHandler.coroutines() {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines}")
}

fun DependencyHandler.coil() {
    implementation("io.coil-kt:coil-compose:${Versions.coil}")
}