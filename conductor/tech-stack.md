# Technology Stack

## Core Development
*   **Language:** Kotlin 2.2.10
*   **Platform:** Android SDK
    *   **Minimum SDK:** 24 (Android 7.0)
    *   **Target SDK:** 36 (Android 15+)
    *   **Compile SDK:** 36

## Frameworks & Libraries
*   **UI Framework:** Jetpack Compose (BOM 2024.09.00)
*   **Design System:** Material 3
*   **Architecture:** MVVM (Model-View-ViewModel) with Compose-based Navigation.
*   **Navigation:** AndroidX Navigation Compose
*   **Serialization:** Kotlinx Serialization
*   **Local Storage:** AndroidX DataStore Preferences
*   **Database:** AndroidX Room (2.7.0+)
*   **Core AndroidX:**
    *   Core KTX (1.17.0)
    *   Lifecycle Runtime KTX (2.10.0)
    *   Activity Compose (1.12.2)
    *   Lifecycle ViewModel Compose

## Build & Infrastructure
*   **Build System:** Gradle (Kotlin DSL)
*   **Android Gradle Plugin (AGP):** 9.0.0
*   **Annotation Processing:** Google KSP
*   **Dependency Management:** Version Catalogs (`libs.versions.toml`)

## Testing
*   **Unit Testing:** JUnit 4
*   **Mocking:** MockK
*   **Simulation:** Robolectric
*   **Async Testing:** Kotlinx Coroutines Test
*   **UI Testing:**
    *   Espresso (3.7.0)
    *   Compose UI Test JUnit 4
*   **Instrumentation:** AndroidJUnitRunner


