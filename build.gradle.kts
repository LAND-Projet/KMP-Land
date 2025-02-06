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
        classpath("app.cash.sqldelight:gradle-plugin:2.0.2")
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

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
