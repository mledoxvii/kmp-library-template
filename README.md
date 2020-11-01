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
