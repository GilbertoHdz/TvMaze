plugins {
    id("com.android.library")
    id("kotlin-kapt")
}

apply(from = "$rootDir/gradle-scripts/base-module.gradle")

android {
    namespace = "com.gilbertohdz.core"
}

dependencies {
    implementation("androidx.media3:media3-exoplayer:1.2.0")
    implementation("androidx.media3:media3-ui:1.2.0")
}