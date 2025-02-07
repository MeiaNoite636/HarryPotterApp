
ksp {
    arg("option_name", "option_value")
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    kotlin("plugin.serialization") version "2.0.21"

    //Hilt
    alias(libs.plugins.hilt.pluggin)
    alias(libs.plugins.ksp.pluggin)

    alias(libs.plugins.google.plugin)

}

android {
    namespace = "com.example.harrypotterapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.harrypotterapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    //Compose Navigation
    implementation(libs.compose.navigation)
    implementation(libs.compose.bottom.navigation)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //Kotlin Serialization
    implementation(libs.kotlin.serialization)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    //Okhttp
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.interceptor)

    implementation(libs.dagger.hilt)
    implementation(libs.hilt.navigation.compose)

    //Hilt
    implementation(libs.dagger.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)


    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}