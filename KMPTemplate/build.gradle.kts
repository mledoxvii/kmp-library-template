plugins {
    kotlin("multiplatform") version "1.4.10"
    id("com.android.library")
    id("kotlin-android-extensions")
}
val projectVersion = "1.0.0"
val projectVersionCode = 1

group = "com.kmp.template"
version = projectVersion

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
kotlin {
    android()
    iosX64("iosSimulator") {
        binaries {
            framework()
        }
    }
    iosArm64("iosDevice") {
        binaries {
            framework()
        }
    }
    iosArm32("iosDeviceOld") {
        binaries {
            framework()
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.2.0")
            }
        }
        val androidTest by getting
        val iosSimulatorMain by getting
        val iosSimulatorTest by getting
    }
}
android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = projectVersionCode
        versionName = projectVersion
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}