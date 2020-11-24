![Build Application](https://github.com/ProtoEnergy/RetailDriver/workflows/Build%20Application/badge.svg)


# Subscriber App
Simple app that uses the MVVM
design pattern for getting data

## Prerequisite

minSdkVersion -> 22

Gradle build system

The project uses Firebase but the google-services.json file is included as part of the project

## TOC

- [Architecture](#architecture)
- [Libraries](#libraries)
- [Extras](#extras)

## Architecture

The App is not organized into multiple modules but follows the same principles of
the Presentation, Domain, and Data Layers.
The presentation layer handles the UI work with the logic contained in the **ViewModel**.
The UI uses a **LiveData** object from the ViewModel and observes it using the **Observer Pattern**.

the ViewModel uses the **viewModelScope** to launch the coroutines while Fragments use the **viewLifeCycleOwner**
to observe data.

## Libraries

This app takes use of the following libraries:

- [Jetpack](https://developer.android.com/jetpack)🚀
  - [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI data to survive configuration changes and is lifecycle-aware
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Notify views when underlying database changes

- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines



## Extras

#### CI-Pipeline

GitHub actions is used for Continuous Integration every time an update is made
to the repo. The configuration is in the .github folder

#### Code Analysis
This code uses detekt for static code analysis on android studio for kotlin

### To come (if needed)

  - [Data Binding](https://developer.android.com/topic/libraries/data-binding) - Declaratively bind observable data to UI elements
  - [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - Manage your Android background jobs
  - [Room DB](https://developer.android.com/topic/libraries/architecture/room) - Fluent SQLite database access
  - [Paging](https://developer.android.com/topic/libraries/architecture/paging) - Load and display small chunks of data at a time
  - [Material Design](https://material.io/develop/android/docs/getting-started/) - build awesome beautiful UIs.🔥🔥

