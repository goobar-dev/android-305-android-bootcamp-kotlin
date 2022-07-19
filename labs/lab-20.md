# 🖥 Lab 20: UI Architecture with Jetpack Compose

## Objectives

1. Setup Google Maps api key
    1. Follow this [link](https://developers.google.com/maps/documentation/android-sdk/get-api-key) to create an api key for Google maps
    2. Add the api key to your user's gradle.properties file as `GOOGLE_MAPS_API_KEY="<your key>"`
    3. Rebuild your project

1. Hoist Main screen state into a `ViewModel`
    1. Create a class named `MainViewModel` in the `main` package
    2. Create a `UiState` data class within `MainViewModel` that includes a single property `locations: List<Location>`
    3. Expose a `StateFlow<UiState>` as a property named `state` from `MainViewModel`
    4. Get an instance of `MainViewModel` from `MainScreen()` by adding `viewModel: MainViewModel = viewModel()` as a function parameter
    5. Collect `MainViewModel.state` as Compose `State` by adding `val state by viewModel.state.collectAsState()` to the beginning of `MainScreen()`
    6. Update `MainScreenContent` to take a `List<Location>` named `locations` and replace `items(LOCATIONS){}` with `items(state.locations){}`
    7. Pass `state.locations` to `MainScreenContent`


3. Hoist Map screen state into a `ViewModel`
    1. Create a class named `MapViewModel` in the `map` package
    2. Create a `UiState` data class within `MapViewModel` that includs 3 properties:
        1. `title: String`
        2. `location: Location`
        3. `markerLabel: String`
        4. `zoom: Float`
    3. Expose a `StateFlow<UiState>` as a property named `state` from `MapViewModel`
    4. Add a constructor property to `MapViewModel` named `location: Location` and use the to initialize the `state` property
    5. Within the same file as `MapViewModel` create a class named `MapViewModelFactory` that implments `ViewModelProvider.Factory` and returns an instance of `MapViewModel` within the overridden `create()` method (see hints)
    6. Add a new parameter to `MapScreen` _after_ the `location` parameter.  This new parameter should be named `viewModel: MapViewModel = viewModel()`
    7. Pass `factory = MapViewModelFactory(location)` to the `viewModel()` call
    8. Collect `MapViewModel.state` as Compose `State` by adding `val state by viewModel.state.collectAsState()` to the beginning of `MapScreen()`
    9. Use that `state` variable, to populate the UI of `MapScreen()`

## Challenges

# 🖥 Lab 20 Hints: UI Architecture with Jetpack Compose

## Creating MapViewModelFactory
```kotlin
class MapViewModelFactory(private val location: Location) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return MapViewModel(location) as T
  }
}
```

## 💡 Helpful Resources
1. [Google Maps Compose Library](https://developers.google.com/maps/documentation/android-sdk/maps-compose)
2. [Compose and your existing architecture](https://developer.android.com/jetpack/compose/interop/compose-in-existing-arch)