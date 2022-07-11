# 🖥 Lab 6: Building a Navigation Graph

## Objectives
1. Create a new Navigation resource file named `main_navigation.xml`
2. Add `MyNotesFragment` and `StudyGuideFragment` as destinations in the navigation graph
3. Open `navigation_items.xml` and update the `android:id` attribute of each menu item to match the `id` of the corresponding destination in `main_navigation.xml`
4. In `activity_main.xml`, replace the existing `Fragment` container `FrameLayout` with a `FragmentContainerView`
   1. Add the following attribute `android:name="androidx.navigation.fragment.NavHostFragment"`
5. Set `main_navigation.xml` as the `navGraph` for the `FragmentContainerView`
6. Remove the manual `FragmentTransaction` code from `MyNotesFragment`
   1. We will be replacing these later in the Lab
7. Remove the `onItemSelectedListener` from `MainActivity`
   1. We will be replacing this later in the Lab
8. Within `MainActivity` get a reference to the `NavController` from the `NavHostFragment`
9. Call `BottomNavigationView.setupWithNavController()` to connect your `BottomNavigationView` with the `NavController`
   1. Your `BottomNavigationView` should now change fragments when you select a tab
10. Add `NoteDetailFragment` and `CreateNoteFragment` as destinations within `main_navigation.xml`
11. Add `Action`s connecting `MyNotesFragment` to both `NoteDetailFragment` and `CreateNoteFragment`
12. Update click handlers in `MyNoteFragment` to navigate to the desired fragments using the generated `Action` ids associated with the `Actions` defined in `main_navigation.xml`
   1. Your app should now navigate to all the fragments as before
   2. Notice however that clicking the Back button closes the app instead of popping the last `Fragment`
13. In `MainActivity`, create an `AppBarConfiguration` and use it to call `Toolbar.setupWithNavController` to connect your `Toolbar` with the navigation graph
   1. Clicking the back button in the `Toolbar` should now properly pop the fragment
   2. This partially addresses back navigation
14. In `MainActivity`, override `onBackPressed()` to first check if `NavController.popBackStack()` returns `true` before calling `super.onBackPressed()`
   1. This should fix the remaining back navigation issue
15. For each destination in `main_navigation.xml` add a `"ShowBottomNav"` argument and specify a boolean value to control whether the `BottomNavigationView` should be visible when that destination is showing
15. Add a `OnDestinationChangedListener` to your `NavController`
16. Within the `OnDestinationChangedListner`, show, or hide, the `BottomNavigationView` based on the `"ShowBottomNav"` argument passed to the callback
17. For both navigation `Action`s in `main_navigation.xml`, add an enter animation using `app:enterAnim="@android:anim/slide_in_left"`

## Challenges
### Animate your screen transitions
Create a custom animation resource to further customize your Navigation animations.
- Look at `@android:anim/slide_out_down` for inspiration.

# 🖥 Lab 6 Hints: Building a Navigation Graph

## 💡 Helpful Resources
- [Getting Started with Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Updating Bottom Navigation with Navigation Component](https://developer.android.com/guide/navigation/navigation-ui?hl=tr#bottom_navigation)
- [Animating Between Destinations](https://developer.android.com/guide/navigation/navigation-animate-transitions)

## 💡 Which dependencies do I need to add?
While the documentation lists quite a few related dependencies, for this Lab you should only need these two dependencies for the Navigation Component
```groovy
dependencies {
  implementation "androidx.navigation:navigation-fragment-ktx:2.5.0"
  implementation "androidx.navigation:navigation-ui-ktx:2.5.0"
}
```

## 💡 How to build my Navigation graph?
A Navigation graph can edited in 2 ways
1. Using the interactive UI designer
2. From XML

Anything you change in the UI designer will be reflected in the XML.

## 💡 How to add a destination to my Navigation graph?
You can do this 2 ways
1. Open the UI designer, and click the `Add Destination` button in the toolbar. Look for the green + icon.
2. In the XML, add a `<Fragment>` tag and specify the `android:id` and `android:name` attributes

## 💡 How to add a Navigation Action from one destination to another?
This can be done 2 ways
1. In the UI designer, drag from one Destination to the other.  This should create a line connecting the two Destinations.  This line represents the Action and can be selected/configured.
2. In the XML, find the `<Fragment>` tag for the destination you're starting from.  Within that tag, add a `<action>` specifying the `android:id` and `app:destination` attributes

## 💡 How to parse an argument from NavController.addOnDestinationChangedListener() ?
Do you need to check whether a Navigation destination includes a specific argument when navigated to?

Let's imagine we're checking the value of `"ShowAppBar"`.  Within our `OnDestinationChangedListener` we could check for the argument value like this:
```kotlin
val showAppBar = arguments?.getBoolean("ShowAppBar", false) == true
```