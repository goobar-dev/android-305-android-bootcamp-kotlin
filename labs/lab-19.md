# 🖥 Lab 19: Create a new Jetpack Compose app
Start building out a simple Jetpack Compose application to list and display locations.

## Objectives

1. Open the `Compose Locations` project in Android Studio

2. Deploy the new project

3. Create a `MainScreen` composeable that displays a list of cards
    1. The `MainScreen` function should use a `Scaffold` as it's first child composeable
    2. The `Scaffold` should define a `topBar` using `SmallTopAppBar`
    3. The `SmallTopAppBar` should have a title of `Compose Locations`
    4. The `content` of the `Scaffold` should be a `LazyColum`
    5. The `LazyColum` should display a list of cards
    6. Each `Card` should represent a "location" and include a title `Text` composable with value `"Location $index"`

4. Create a composeable preview that displays `MainContent`

5. Create a `MapScreen` composeable that displays a selected location
    1. The `MapScreen` composeable should take a parameter `index: Int` and a parameter `onBackClick: () -> Unit`
    2. It should include a `Scaffold`
    3. The `Scaffold` should have a `topBar` using `SmallTopAppBar`
    4. The `SmallTopAppBar` should define a back button using `navigationIcon` and `IconButton`
    5. Clicking the back icon should call the `onBackClick` function

6. Create a variable controlling whether the `MainContent` or `MapContent` composable is being shown
    1. This can be done using `remember{}` and `mutableStateOf()` like this `var selectedIndex: Int? by remember { mutableStateOf(null) }`
    2. If `selectedIndex` is null, show `MainScreen`
    3. If `selectedIndex` is not null, show `MapScreen`


## Challenges

# 🖥 Lab 19 Hints: Create a new Jetpack Compose app

## 💡 How do I change which composeable is being drawn?
```kotlin
var selectedIndex: Int? by remember { mutableStateOf(null) }

if (selectedIndex == null) {
  MainScreen() { index ->
    selectedIndex = index
  }
} else {
  MapScreen(index = selectedIndex!!) {
    selectedIndex = null
  }
}
```

## 💡 Helpful Resources
1. [Jetpack Compose UI Toolkit](https://developer.android.com/jetpack/compose)
2. [Get Started with Jetpack Compose](https://developer.android.com/jetpack/getting-started)
3. [Material Design 3](https://m3.material.io/)
