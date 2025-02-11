import dev.icerock.gradle.MRVisibility

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(google.plugins.googleServices)
    alias(other.plugins.ktlint)
    alias(moko.plugins.mokoResources)
    //alias(sqldelight.plugins.initiliazer)
    //id("app.cash.sqldelight") version "2.0.2" apply false
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    task("testClasses")
    jvmToolchain(21)
    /*targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries.withType<org.jetbrains.kotlin.gradle.plugin.mpp.Framework> {
            linkerOpts.add("-lsqlite3")
        }
    }*/

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
        val commonMain by getting {
            dependencies {
                api(moko.mokoLibResources)
                api(moko.mokoCompose)
                implementation("dev.icerock.moko:parcelize:0.8.0")
                //implementation(sqldelight.sqlCommon)

                implementation(ktor.ktorCore)
                implementation(ktor.ktorCio)

                implementation(coroutines.coroutineCoreKMM)

                implementation(kotlinx.kotlinxSerialization)
                implementation(kotlinx.kotlinxDatetime)

                implementation(other.napier)
                implementation(other.slf4j)

                api(koin.koinCore)

                implementation(firebase.firebaseAuthentication)
                implementation(firebase.firebaseFirestore)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val androidMain by getting {
            kotlin.srcDir("build/generated/moko/androidMain/src")
            dependencies {
                //implementation(sqldelight.sqlAndroid)
                implementation(ktor.ktorAndroid)
                //api(moko.mokoCompose)
            }
        }
        androidNativeTest.dependencies {
            implementation(test.junit)
            implementation(test.mockk)

            //implementation(sqldelight.sqlDriver)
            //implementation(sqldelight.sqlJKvm)
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                //implementation(sqldelight.sqlIos)
                implementation(ktor.ktorIos)
                implementation(other.touchlabStately)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

/*sqldelight {
    databases {
        create("IdeaDatabase") {
            packageName.set("com.kmp.idea.database")
            srcDirs.setFrom("src/commonMain/sqldelight")
        }
    }
    linkSqlite = true
}*/

android {
    namespace = "com.kmp.idea"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

multiplatformResources {
    resourcesPackage.set("com.kmp.idea")
    resourcesClassName.set("SharedRes")
    resourcesVisibility.set(MRVisibility.Internal)
    iosBaseLocalizationRegion.set("en")
    iosMinimalDeploymentTarget.set("16.0")
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    verbose.set(true)
    ignoreFailures.set(false)
    //disabledRules.set(setOf("final-newline", "no-wildcard-imports","function-naming"))
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF)

    }
}