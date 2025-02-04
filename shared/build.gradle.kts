plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(google.plugins.googleServices)
    alias(other.plugins.ktlint)
    alias(moko.plugins.mokoResources)
    alias(sqldelight.plugins.sqldelightId)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries.withType<org.jetbrains.kotlin.gradle.plugin.mpp.Framework> {
            linkerOpts.add("-lsqlite3")
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            export(moko.mokoLibResources)
            export(moko.mokoGraphics)
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
        extraSpecAttributes["exclude_files"] = "['src/commonMain/resources/MR/**']"
    }
    
    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            api(moko.mokoLibResources)
            implementation(sqldelight.sqlCommon)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(sqldelight.sqlAndroid)
        }
        androidNativeTest.dependencies {
            implementation(sqldelight.sqlDriver)
            implementation(sqldelight.sqlJKvm)
        }
        iosMain.dependencies {
            implementation(sqldelight.sqlIos)
        }
    }
}

sqldelight {
    databases {
        create("IdeaDatabase") {
            packageName.set("com.kmp.idea.database")
            srcDirs.setFrom("src/commonMain/sqldelight")
        }
    }
    linkSqlite = true
}

android {
    namespace = "com.kmp.idea"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.kmp.idea" // required
    multiplatformResourcesClassName = "SharedRes"
    disableStaticFrameworkWarning = true
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    verbose.set(true)
    ignoreFailures.set(false)
    disabledRules.set(setOf("final-newline", "no-wildcard-imports"))
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF)
    }
}