UCC IT Department App

This project is now structured as a Kotlin Multiplatform app with:
- Android support
- Web support through Kotlin Wasm and Compose Multiplatform

Group Members
- Kadian Morris
- Charma Whorms

Open the project
Open this folder in Android Studio using File -> Open -> UccItMobileApp.

Requirements
- JDK 17
- Android Studio with Gradle support

If Gradle sync complains about JDK, set the Gradle JDK to 17 in Android Studio settings.

Run on Android
- Use Android Studio to run the app on an emulator or device
- Or run: ./gradlew installDebug

Run on the Web
- Start the web development server with: ./gradlew :app:wasmJsBrowserDevelopmentRun
- Then open the local browser URL shown by Gradle

Build the Web Target
- Compile the web target with: ./gradlew :app:compileKotlinWasmJs

Notes
- The Android app still contains Android-specific features such as intents, Room, and WebView-based behavior.
- The web target currently uses a shared Compose UI that exposes the main department content in the browser.
