plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
}


buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven {
            url = uri("http://maven.nodle.io")
            isAllowInsecureProtocol = true
        }
    }
    dependencies {
        classpath(moko.mokoResourcesGenerator)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("http://maven.nodle.io")
            isAllowInsecureProtocol = true
        }
    }
}

