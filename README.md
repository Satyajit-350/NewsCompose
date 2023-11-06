<img src="screenshots/app_logo.png" height="150"/>

# NewsCompose
A simple News Application built with Jetpack Compose. This app follows a clean architecture design, making it robust and maintainable. It seamlessly integrates modern technologies like Retrofit and Room DB to efficiently handle data, while adhering to the MVVM (Model-View-ViewModel) pattern for structured and scalable code.

## Techs Used 💻
- 100% [Kotlin](https://kotlinlang.org/) based
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - modern toolkit for building native Android UI.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [Flow](https://developer.android.com/kotlin/flow)
- [Dagger-Hilt](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that is lifecycle aware (didn't destroyed on UI changes).
- [Android Architecture Components](https://developer.android.com/topic/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - MVVM Architecture (Declarative View - ViewModel - Model)
  - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Room Database](https://developer.android.com/training/data-storage/room) - save data in a local database

 ## Application Install
You can Install and test the app from below 👇

[![NewsCompose](https://img.shields.io/badge/NewsCompose-APK-silver.svg?style=for-the-badge&logo=android)](https://github.com/Satyajit-350/Map-Explore/releases/tag/1.0.0)

## Build and Run Instructions

1. Clone the repository to your local machine using the following command:
```XML

git clone git@github.com:Satyajit-350/NewsCompose.git

```
2. Open the project in Android Studio.

3. Add the API KEY in project local.properties file
```XML

API KEY = YOUR API KEY

```
4. Build the project 

5. Run the app on an Android emulator or a physical device by selecting the target device and clicking on the **Run**

## Sceenshots
<h2 align="center">Light Mode</h2>

| ![](https://github.com/Satyajit-350/NewsCompose/blob/master/screenshots/light/onboarding_1_light.png) | ![](https://github.com/Satyajit-350/NewsCompose/blob/master/screenshots/light/onboarding_2_light.png) | ![](https://github.com/Satyajit-350/NewsCompose/blob/master/screenshots/light/onboarding_3_light.png) |
|-------------------------------------------------------|-------------------------------------------------------|-------------------------------------------------------|
| ![](https://github.com/Satyajit-350/NewsCompose/blob/master/screenshots/light/home_light.png) | ![](https://github.com/Satyajit-350/NewsCompose/blob/master/screenshots/light/Search_light.png) | ![](https://github.com/Satyajit-350/NewsCompose/blob/master/screenshots/light/bookmarks_light.png) | 

<h2 align="center">Dark Mode</h2>

| ![](https://github.com/Satyajit-350/NewsCompose/blob/master/screenshots/dark/onboarding_1_dark.png) | ![](https://github.com/Satyajit-350/NewsCompose/blob/master/screenshots/dark/onboarding_2_dark.png) | ![](https://github.com/Satyajit-350/NewsCompose/blob/master/screenshots/dark/onboarding_3_dark.png) |
|-------------------------------------------------------|-------------------------------------------------------|-------------------------------------------------------|
| ![](https://github.com/Satyajit-350/NewsCompose/blob/master/screenshots/dark/Details_dark.png) | ![](https://github.com/Satyajit-350/NewsCompose/blob/master/screenshots/dark/search_dark.png) | ![](https://github.com/Satyajit-350/NewsCompose/blob/master/screenshots/dark/Home_dark.png) | 

This is my first app built with Jetpack compose. It is a simple app with limited feature but almost every concept of jetpack compose are covered in this project.
