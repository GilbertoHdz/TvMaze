plugins {
    id("com.android.library")
    id("kotlin-kapt")
}

apply(from = "$rootDir/gradle-scripts/compose-module.gradle")

android {
    namespace = "com.gilbertohdz.core"
}

dependencies {
    implementation(libs.media3.exoplayer)
    implementation(libs.media3.exoplayer.ui)
    implementation(libs.media3.exoplayer.hls)
    implementation(libs.media3.exoplayer.dash)
}