# 🖥 Lab 10: MVVM with Android Jetpack
Use Android Jetpack apis to implement an MVVM UI architecture in our app.

## Objectives

1. In the `studyguide` package, create a new class `StudyGuideViewModel`

2. Make `StudyGuideViewModel` extend `ViewModel`
    1. The `ViewModel` class will help us retain state across orientation changes and act as a layer of separation between our UI and Data application layers

3. Create a property named `title` of type `MutableStateFlow<String>` and initialize it with a value of `"Study Guide"`

4. Within `StudyGuideFragment` create a property `viewModel` and initialize it using `val viewModel: StudyGuideViewModel by viewModels()`
    1. This delegate will ensure we get the same `Fragment` instance back across `Fragment` recreation

5. Open `fragment_study_guide.xml` and provide an id named `titleTextView` for the `TextView` so we can udpate its value from `StudyGuideFragment`

6. Override `onViewCreated()` within `StudyGuideFragment`

7. Within `onViewCreated()`, lanuch a new coroutine using `lifecycleScope.launch{}`.
    1. This will provide us with a coroutine which can be used to asynchronously collect changes in our `title` `StateFlow`

8. Inside the `launch{}` call, call `repeateOnLifecycle(Lifecycle.State.STARTED) {}`
    1. Within this call, we can setup the collection of our UI state, to ensure we only respond to UI changes while the `Fragment` is active on screen

9. Inside `repeatOnLifecycle{}` call `collect{}` on `viewModel.title` and update `binding.titleTextView` with the new title value

10. Open `StudyGuideViewModel` and add an `init{}` block so we can run some code when our `ViewModel` is instantiated

11. Within `init{}` use `viewModelScope.launch{}` to create a new coroutine.

12. Make a call to `delay(3000)` to simulate an async operation by suspending the coroutine for 3 seconds

13. After the `delay()` call, update the value of the `title` `StateFlow` to `Study Guide Fragment` using `title.update{ "Study Guide Fragment" }`

14. Redploy the app and notice how the title `TextView`'s value changes automatically after the 3 second delay

15. Create a new `data` package in your root project directory

16. Create a `Note` data class with the following properties
    1. `title: String`
    2. `category: String`
    3. `content: String`

17. In `StudyGuideViewModel`, create another `StateFlow` on type `Note` and initialize it to provide a note with some placeholder content

18. In `StudyGuideViewModel`, create a data class `UiState` with the following properties
    1. `title: String`
    2. `ntoes: List<Note>`

19. Remove the two existing `StateFlow` properties, and replace them with a single property of type `MutableStateFlow<UiState>`
    1. Update the initial value to provide the inital title and an empty set of `Note`s
    2. Refactor the updating of the title within `init` to `state.update { currentValue -> currentValue.copy(title = "Study Guide Fragment") }`
    3. Update the `Flow` collection within `StudyGuideFragment` to bind the title properly


## Challenges

1. Update `MyNotesFragment` to use a `ViewModel` with a `UiState` that controls the displayed title `TextView`
2. Update `NoteDetailFragment` to use a `ViewModel` with a `UiState` that controls the displayed title, category, and content
3. Update `CreateNoteFragment` to use a `ViewModel` with a `UiState` that controls the categories displayed in the `Spinner` dropdown



# 🖥 Lab 10 Hints: MVVM with Android Jetpack

1. [Android Jetpack ViewModel Overview](https://developer.android.com/topic/libraries/architecture/viewmodel)
2. [Using ViewModel to shared data betwween Fragments](https://developer.android.com/topic/libraries/architecture/viewmodel)
3. [Using StateFlow in Android development](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow)
4. [StateFlow and SharedFlow](https://blog.jetbrains.com/kotlin/2020/10/kotlinx-coroutines-1-4-0-introducing-stateflow-and-sharedflow/)
