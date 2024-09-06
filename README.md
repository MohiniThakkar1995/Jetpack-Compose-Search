# Jetpack-Compose-Search

Search Functionality with Jetpack Compose

- MutableStateFlow to hold the search query and expose it as StateFlow.
- Debounce Operator for Search Query
- Apply the debounce operator to delay the emission of search queries to avoid unnecessary rapid requests.
- Filtering Data Based on Search Query
- Collect the filtered data in your Jetpack Compose UI and display it in a LazyColumn or similar component.

Explored Google's screenshot testing framework:
Add Plugin Dependency:

Add the plugin to your project:
```
screenshot = { id = "com.android.compose.screenshot", version.ref = "screenshot" }
```
  
Update Screenshots:

Run the command to update screenshots from the preview:
```
./gradlew updateDebugScreenshotTest
```

Validate Screenshots:
Run the command to validate screenshots:
```
./gradlew validateDebugScreenshotTest
```
