plugins {
    id("com.android.library")
    id("kotlin-kapt")
}

apply(from = "$rootDir/gradle-scripts/base-module.gradle")

android {
    namespace = "com.gilbertohdz.domain"
}

dependencies {

    val lifecycle_version = "2.2.0"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
}