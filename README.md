# Kotlin Multiplatform Library Template

This is a template project to create a Kotlin Multiplatform Library for iOS and Android.

## Installation

Clone this repository and copy the `KMPTemplate` folder wherever you want to create your library.

**IMPORTANT**: The project contains symlinks. Make sure they are not lost when copying it to a new location. Use the `-a` flag instead of `-rf` when copying it through the command line.
```bash
cp -a ./KMPTemplate /path/to/new/location
```

### Change Project Name and Group

You will probably want to use your own group and name for the project.

Follow these steps to change the project name:

1. Rename the project folder
2. Change the project name inside `settings.gradle.kts`:
```kotlin
rootProject.name = "YourProjectName"
```

Follow these steps to change the project group:

1. Change group inside `build.gradle.kts`:
```kotlin
group = "your.custom.group"
```
2. Change Android package name inside `main/AndroidManifest.xml`:
```xml
<manifest ... package="your.custom.group.android">
```

## iOS Architectures

The project contains 3 iOS targets, one for each architecture with their own source folders:

* `iosDeviceMain` / `iosDeviceMainTest`: For `arm64` architecture.
* `iosDeviceOldMain` / `iosDeviceOldTest`: For `arm32` architecture.
* `iosSimulatorMain` / `iosSimulatorTest`: For `x64` architecture.

The `arm64` and `arm32` source folders are symlinks to the simulator's source folders. This way, they all share the same files, so adding or edditing files in one target will automatically be reflected in the others.

## Generate Libraries

### Android

To generate the Android library execute the `Generate Android Release` run-configuration. The library will be generated inside `build/outputs/aar` folder.

### iOS

To generate the iOS framework execute the `Generate iOS Release` run-configuration. This executes the custom `generateIosFramework` gradle task, which bundles the different architectures frameworks into an `xcframework`. The output will be generated inside `build/outputs/ios` folder.
