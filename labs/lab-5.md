# 🖥 Lab 5: Working with Activities and Fragments
In this lab, we will setup a basic navigation infrastructure for a new Study app

## Objectives
0. Create a new Android Studio project named `Android Study Guide`
1. Ensure you have an empty Kotlin Activity named `MainActivity`
    1. This should be set as the app's launcher activity in `AndroidManifest.xml`
2. Create a second Kotlin Activity named `TwitterActivity`
    1. This will be used later this week to integrate with Twitter's PWA
3. Create 4 Fragments that will become the core screens in our app
    1. `CreateNoteFragment`
    2. `NoteDetailFragment`
    3. `MyNotesFragment`
    4. `StudyGuideFragment`
4. For each `Activity` & `Fragment` add a `TextView` to the layout that uniquely identifies that screen
5. Add a `Button` to `MainActivity` that shows `TwitterActivity` when clicked
6. Add 4 `Buttons` to `MainActivity` that show each of the new `Fragments` when clicked

## Challenges
### Logging the Android `Activity` lifecycle
Using `Logcat`, add log messages to key lifecycle events in an `Activity` and `Fragment` to observe lifecycle changes as you interact with your app.

### Changing Launcher `Activities`
Temporarily change your app's launcher `Activity` to `TwitterActivity` and observe the impact on your app

# 🖥 Lab 5: Working with Activities and Fragments

## 💡 Helpful Resources
- [How to Create a Fragment?](https://developer.android.com/guide/fragments/create)
- [TextView Documentation](https://developer.android.com/reference/android/widget/TextView)
- [Button Documentation](https://developer.android.com/reference/android/widget/Button)
- [Material Design Button Guidance](https://material.io/components/buttons)

## 💡 Creating new Activities and Fragments
Android Studio can help us create a new `Activity` or `Fragment`.

However, the IDE often presents us with a handful of different templates such as `EmptyActivity`, `Fragment with ViewModel`, `List Activity`, etc

These can be kind of fun to play with when starting out, but more most situations you're going to want to choose `EmptyActivity` and `BlankFragment` so you can customize exactly how you want without having to remove stubbed out code.

## 💡 Logging to Logcat
The Android SDK provides a default set of logging functions:
- `Log.d()`
- `Log.e()`
- `Log.i()`
- etc

Adding these to your code can be a helpful way to observe the lifecycle of your application or to help track down bugs.

Output from these functions will go to `Logcat` which can be observed in the `Locat` tool window or using `adb`