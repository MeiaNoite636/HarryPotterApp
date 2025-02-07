// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    //Hilt
    alias(libs.plugins.hilt.pluggin) apply false
    alias(libs.plugins.ksp.pluggin) apply false

    //Firebase
    alias(libs.plugins.google.plugin) apply false
}