import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(google.plugins.mapsSecrets)
    alias(other.plugins.ktlint)
}

allprojects {
    repositories {
        maven(url = "https://jitpack.io")
    }
}

android {
    namespace = "com.kmp.idea.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.kmp.idea.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        resValue(
            "string",
            "GOOGLE_API_KEY",
            "\"${properties.getProperty("GOOGLE_API_KEY")}\"",
        )
        resValue(
            "string",
            "GOOGLE_APP_ID",
            "\"${properties.getProperty("GOOGLE_APP_ID")}\"",
        )
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    signingConfigs {
        create("release") {
            storeFile = file("/path/to/your/KEYSTORE ANDROID/yourkey")
            storePassword = "yourStorePassword"
            keyAlias = "alias"
            keyPassword = "yourpassword"
        }
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

tasks.getByPath("preBuild").dependsOn("ktlintFormat")

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    android.set(true)
    ignoreFailures.set(false)
    disabledRules.set(setOf("final-newline", "no-wildcard-imports", "function-naming"))
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF)
    }
}

dependencies {
    implementation(projects.shared)

    implementation(date.datePickerCore)
    implementation(date.datePickerCalendar)

    implementation(androidx.coreKtx)
    implementation(androidx.appCompat)
    implementation(androidx.composeUI)
    implementation(androidx.composeUITooling)
    implementation(androidx.composeUIToolingPreview)
    implementation(androidx.composeFoundation)
    implementation(androidx.composeMaterial)
    implementation(androidx.workerKtx)

    implementation(coroutines.coroutineAndroid)

    implementation(other.sweetToast)
    implementation(other.nodleSdk)
    implementation(other.rxPermission)
    implementation(other.twyper)

    implementation(platform(firebase.androidFirebase))
    implementation(firebase.androidFirebaseKTXStorage)
    implementation(firebase.androidFirebaseMessaging)
    implementation(firebase.androidFirebaseMessagingKTX)
    implementation(firebase.androidFirebaseAnalytics)

    implementation(retrofit.retrofitMain)
    implementation(retrofit.retrofitGson)

    implementation(moshi.moshiMain)
    implementation(moshi.moshiKotlin)

    implementation(koin.koinCore)
    implementation(koin.koinAndroid)
    implementation(koin.koinAndroidxCompose)

    implementation(compose.navigation)
    implementation(compose.viewModelCompose)
    implementation(compose.animationCompose)
    implementation(compose.activityCompose)

    implementation(coil.coilCompose)

    implementation(google.composeMap)
    implementation(google.gmsGoogleMap)
    implementation(google.googlePlaces)
    implementation(google.guava)
    implementation(google.playAdsService)
    implementation(google.auth)

    implementation(materialdesign.materialD3)
    implementation(materialdesign.materialD3WindowSize)

    implementation(camerax.cameraXLifeCycle)
    implementation(camerax.cameraXVideo)
    implementation(camerax.cameraXView)
    implementation(camerax.cameraXExtension)
    implementation(camerax.cameraXCAM)
    implementation(camerax.exifInterface)

    implementation(accompanist.permission)

    implementation(google.reviewMain)
    implementation(google.reviewKtx)

    implementation(kotlinx.kotlinxDatetime)
    implementation(platform(kotlinx.kotlinBom))

    implementation(other.geoLib)
    implementation(other.canopasView)
}
