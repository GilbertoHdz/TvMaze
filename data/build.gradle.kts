plugins {
    id("com.android.library")
    id("kotlin-kapt")
}

apply(from = "$rootDir/gradle-scripts/base-module.gradle")


android {
    namespace = "com.gilbertohdz.data"
}

dependencies {
    implementation(project(":domain"))


    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)

    implementation(libs.okhttp3)
    implementation(libs.okhttp3.interceptor)

    "kapt"(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
}