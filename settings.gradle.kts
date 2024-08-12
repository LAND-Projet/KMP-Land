enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("accompanist") {
            from(files("gradle/libs.accompanist.toml"))
        }
        create("androidx") {
            from(files("gradle/libs.androidx.toml"))
        }
        create("camerax") {
            from(files("gradle/libs.camerax.toml"))
        }
        create("coil") {
            from(files("gradle/libs.coil.toml"))
        }
        create("compose") {
            from(files("gradle/libs.compose.toml"))
        }
        create("coroutines") {
            from(files("gradle/libs.coroutines.toml"))
        }
        create("date") {
            from(files("gradle/libs.date.toml"))
        }
        create("firebase") {
            from(files("gradle/libs.firebase.toml"))
        }
        create("google") {
            from(files("gradle/libs.google.toml"))
        }
        create("koin") {
            from(files("gradle/libs.koin.toml"))
        }
        create("kotlinx") {
            from(files("gradle/libs.kotlinx.toml"))
        }
        create("ktor") {
            from(files("gradle/libs.ktor.toml"))
        }
        create("materialdesign") {
            from(files("gradle/libs.materialdesign.toml"))
        }
        create("moko") {
            from(files("gradle/libs.moko.toml"))
        }
        create("moshi") {
            from(files("gradle/libs.moshi.toml"))
        }
        create("other") {
            from(files("gradle/libs.other.toml"))
        }
        create("retrofit") {
            from(files("gradle/libs.retrofit.toml"))
        }
        create("sqldelight") {
            from(files("gradle/libs.sqldelight.toml"))
        }
        create("test") {
            from(files("gradle/libs.test.toml"))
        }
    }
}

rootProject.name = "KMP-Idea"
include(":androidApp")
include(":shared")