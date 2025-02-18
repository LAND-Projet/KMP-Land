import dev.icerock.gradle.MRVisibility
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(other.plugins.ktlint)
    alias(moko.plugins.mokoResources)
    //alias(libs.plugins.sqldelight)
    //id("app.cash.sqldelight") version "2.0.2"
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
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
            api(moko.mokoLibResources)
            //api(moko.mokoCompose)
            //implementation("dev.icerock.moko:parcelize:0.8.0")

            //implementation(sqldelight.sqlCoroutines)

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
        commonTest.dependencies {
            implementation(libs.kotlin.test)
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
        iosMain.dependencies {
            //implementation(sqldelight.sqlIos)
            implementation(ktor.ktorIos)
            implementation(other.touchlabStately)
        }
    }
}

/*sqldelight {
    databases {
        create("IdeaDB") {
            packageName.set("com.kmp.idea.database")
            //srcDirs.setFrom("src/commonMain/sqldelight")
        }
    }
}*/

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
    resourcesPackage.set("com.kmp.idea")
    resourcesClassName.set("SharedRes")
    resourcesVisibility.set(MRVisibility.Internal)
    iosBaseLocalizationRegion.set("en")
    iosMinimalDeploymentTarget.set("16.0")
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    verbose.set(true)
    ignoreFailures.set(false)
    disabledRules.set(setOf("final-newline", "no-wildcard-imports","function-naming"))
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF)

    }
}