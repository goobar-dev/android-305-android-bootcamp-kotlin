# 🖥 Lab 5: Working with Activities and Fragments
In this lab, we will setup a basic navigation infrastructure for a new Study app

## 📝 Objectives
0. Create a new Android Studio project named `Android Study Guide`

1. Ensure you have an empty Kotlin `Activity` named `MainActivity`
    1. This should be set as the app's launcher activity in `AndroidManifest.xml`

2. Create a second Kotlin `Activity` named `TwitterActivity`
    1. This will be used later this week to integrate with Twitter's PWA

3. Create 4 Fragments that will become the core screens in our app
    1. `CreateNoteFragment`
    2. `NoteDetailFragment`
    3. `MyNotesFragment`
    4. `StudyGuideFragment`

4. For each `Activity` & `Fragment` add a `TextView` to the layout that uniquely identifies that screen

5. Add a `Button` to `MainActivity` that shows `TwitterActivity` when clicked

6. Add 4 `Buttons` to `MainActivity` that show each of the new `Fragments` when clicked
    1. To show a `Fragment`, you'll need to add a `FrameLayout` to `activity_main.xml` to act as the `Fragment` container.
    2. Give the `FrameLayout` an id of `fragmentContainer`
    3. Make the `FrameLayout` fill the layout.
    4. Give the `FrameLayout` an elevation of `4dp`.
        1. This is just temporary to ensure the buttons aren't drawn above the container.  We'll replace this in the next lab.
    6. Add the 4 `Buttons` and give each a label to indicate which `Fragment` they will show.
    7. Add click handlers to each button within `MainActivity`
    8. Within each click handler, perform a `replace()` `FragmentTransaction` to show the desired `Fragment`

## ✨ Challenges
### 1) Logging the Android `Activity` and `Fragment` lifecycles
Using `Logcat`, add log messages to key lifecycle events in an `Activity` and `Fragment` to observe lifecycle changes as you interact with your app.

### 2) Changing Launcher `Activities`
Temporarily change your app's launcher `Activity` to `TwitterActivity` and observe the impact on your app

&nbsp;
# 🖥 Lab 5: Working with Activities and Fragments

## 💡 Helpful Resources
- [How to Create a Fragment?](https://developer.android.com/guide/fragments/create)
- [TextView Documentation](https://developer.android.com/reference/android/widget/TextView)
- [Button Documentation](https://developer.android.com/reference/android/widget/Button)
- [Material Design Button Guidance](https://material.io/components/buttons)

&nbsp;
## 💡 Creating new Activities and Fragments
Android Studio can help us create a new `Activity` or `Fragment`.  
Right-click on your main package within `src/main`, select `New` -> `Activity`/`Fragment` and select the template you want to use.

However, the IDE often presents us with a handful of different templates such as `EmptyActivity`, `Fragment with ViewModel`, `List Activity`, etc

These can be kind of fun to play with when starting out, but more most situations you're going to want to choose `EmptyActivity` and `BlankFragment` so you can customize exactly how you want without having to remove stubbed out code.

&nbsp;
## 💡 Showing a Fragment in an Activity
To show a `Fragment` we need 2 things:
  - We need a `ViewGroup` to act as a container for the `Fragment`.  In this case, we could use a `FrameLayout` that fills `activity_main.xml`.
  - We need to use the `FragmentManager` to create `FragmentTransactions` that add/remove `Fragments` from the container.

Here is a sample method that you can add to `MainActivity` to allow for showing `Fragments` within a container with an id of `fragmentContainer`.
```kotlin
private fun showFragment(fragment: Fragment, name: String) {
    supportFragmentManager
      .beginTransaction()
      .replace(R.id.fragmentContainer, fragment)
      .addToBackStack(name)
      .commit()
  }
```  

&nbsp;
## 💡 Creating an Intent to show the TwitterActivity
Intents are a very general-purpose tool in Android.  We can create them in a variety of ways to achieve a desired result.
In this case, we want to create an "explicit Intent" to open a specific `Activity` - our `TwitterActivity`.
To do that, we need 2 things:
  - a `Context`.  This is usually going to be an instance of an `Activity`.
  - the class name for the `Activity` we want to start.

Once we've created an `Intent`, we can use `Activity.startActivity(intent)` to start the desired `Activity`

```kotlin
binding.showTwitterButton.setOnClickListener {
  val showTwitterIntent = Intent(this@MainActivity, TwitterActivity::class.java)
  startActivity(showTwitterIntent)
}
```
&nbsp;
## 💡 Logging to Logcat
The Android SDK provides a default set of logging functions:
- `Log.d()`
- `Log.e()`
- `Log.i()`
- etc

Adding these to your code can be a helpful way to observe the lifecycle of your application or to help track down bugs.

Output from these functions will go to `Logcat` which can be observed in the `Locat` tool window or using `adb`
