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
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
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

task("generateIosFramework") {
    val buildType = "release"

    dependsOn("link${buildType.capitalize()}FrameworkIosDevice")
    dependsOn("link${buildType.capitalize()}FrameworkIosDeviceOld")
    dependsOn("link${buildType.capitalize()}FrameworkIosSimulator")

    doLast {
        val frameworkName = rootProject.name
        val frameworkDir = "${frameworkName}.framework"
        val outDir = "${buildDir.absolutePath}/outputs/ios"
        val binDir = "${buildDir.absolutePath}/bin"
        val deviceFramework = "${binDir}/iosDevice/${buildType}Framework/${frameworkDir}"
        val oldDeviceFramework = "${binDir}/iosDeviceOld/${buildType}Framework/${frameworkDir}"
        val simulatorFramework = "${binDir}/iosSimulator/${buildType}Framework/${frameworkDir}"
        val deviceFatFramework = "$binDir/iosDeviceFat/$frameworkDir"
        val deviceBinary = "${deviceFramework}/${frameworkName}"
        val oldDeviceBinary = "${oldDeviceFramework}/${frameworkName}"
        val deviceFatBinary = "$deviceFatFramework/$frameworkName"
        val xcFramework = "$outDir/$frameworkName.xcframework"

        copy {
            from(deviceFramework)
            into(deviceFatFramework)
        }

        exec {
            executable("sh")
            args("-c", "lipo $deviceBinary $oldDeviceBinary -create -output $deviceFatBinary")
        }

        delete(outDir)

        exec {
            executable("xcodebuild")
            args(
                "-create-xcframework",
                "-framework", simulatorFramework,
                "-framework", deviceFatFramework,
                "-output", xcFramework
            )
        }
    }
}
