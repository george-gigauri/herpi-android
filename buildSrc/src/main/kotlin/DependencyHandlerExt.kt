import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(d: String) {
    add("implementation", d)
}

fun DependencyHandler.debugImplementation(d: String) {
    add("debugImplementation", d)
}

fun DependencyHandler.testImplementation(d: String) {
    add("testImplementation", d)
}

fun DependencyHandler.androidTestImplementation(d: String) {
    add("androidTestImplementation", d)
}

fun DependencyHandler.ksp(d: String) {
    add("ksp", d)
}

fun DependencyHandler.kapt(d: String) {
    add("kapt", d)
}