# 🖥 Lab 6: Building a Navigation Graph
We will build out the navigation infrastructure for our application using the Navigation component from Android Jetpack.

## Objectives
1. Create a new Navigation resource file named `main_navigation.xml`

2. Add `MyNotesFragment` and `StudyGuideFragment` as destinations in the navigation graph

3. In `activity_main.xml`, replace the existing `Fragment` container `FrameLayout` with a `FragmentContainerView`
   1. Add the following attribute `android:name="androidx.navigation.fragment.NavHostFragment"`

4. Set `main_navigation.xml` as the `navGraph` for the `FragmentContainerView`

5. Remove the manual `FragmentTransaction` code from `MyNotesFragment`
   1. We will be replacing these later in the Lab

6. Within `MainActivity` get a reference to the `NavController` from the `NavHostFragment`
   1. Use `binding.fragmentContainer.getFragment<NavHostFragment>().navController`

7. Create a menu resource named `navigation_items.xml`
   1. Add 2 menu items whose ids match the ids of the navigation destinations within `main_navigation.xml`
   2. Give each menu item a human-readable label

8. Add a `BottomNavigationView` to `activity_main.xml`
   1. remove existing buttons and code referencing them
   2. add the bottom nav view and constrain it to bottom of parent
   3. adjust the fragment container to be constrained to the top of the bottom navigation view
   4. define the items in our bottom nav view by setting the menu attribute to our `navigation_items.xml` menu - `app:menu="@menu/navigation_items"`

9. Call `BottomNavigationView.setupWithNavController()` to connect your `BottomNavigationView` with the `NavController`
   1. Your `BottomNavigationView` should now change fragments when you select a tab

10. Add `NoteDetailFragment` and `CreateNoteFragment` as destinations within `main_navigation.xml`

11. Add `Action`s connecting `MyNotesFragment` to both `NoteDetailFragment` and `CreateNoteFragment`

12. Add a "show details" Button and a "create note" button to `MyNotesFragment`

13. Update click handlers in `MyNoteFragment` to navigate to the desired fragments using the generated `Action` ids associated with the `Actions` defined in `main_navigation.xml`
   1. Your app should now navigate to all the fragments as before
   2. Notice however that clicking the Back button closes the app instead of popping the last `Fragment`

14. Connect `Toolbar` to `NavController`
   1. Change app theme to implement `DayNight.NoActionBar`
   2. Add `androidx.appcompat.widget.Toolbar` to the top of your `activity_main.xml` layout
   3. Update the contrainst for you `FragmentContainerView` so it sits below the `Toolbar` and above the `BottomNavigationView`
   4. From `MainActivity` call `setSupprtActionBar()` and pass in a reference to your `Toolbar`
   5. Create an instance of `AppBarConfiguration` and pass in `setOf(id.myNotesFragment, id.studyGuideFragment)`
   6. Call `Toolbar.setupWithNavController()` and pass in the reference to the `NavController` and `AppBarConfiguration`.  This will prevent the back icon showing in the `Toolbar` when we visit either of our bottom navigation destinations. 

15. In `MainActivity`, override `onBackPressed()` to first check if `NavController.popBackStack()` returns `true` before calling `super.onBackPressed()`
   1. This should prevent the app from closing if we have navigated to a `Fragment` destination
   2. Our navigation is still not ideal however.  Our `Toolbar` and `BottomNavigationView` still don't behave as we want.

16. For each destination in `main_navigation.xml` add a `"ShowBottomNav"` argument and specify a boolean value to control whether the `BottomNavigationView` should be visible when that destination is showing

17. Add a `OnDestinationChangedListener` to your `NavController`

18. Within the `OnDestinationChangedListner`, show, or hide, the `BottomNavigationView` based on the `"ShowBottomNav"` argument passed to the callback

19. For both navigation `Action`s in `main_navigation.xml`, add an enter animation using `app:enterAnim="@android:anim/slide_in_left"`

## Challenges

### Improve the UI polish
1. Give user-friendly names to each navigation destination to improve the look of the `Toolbar` title.
   1. Check the `label` attribute of each destination in `main_navigation.xml`
3. Give icons to the `BottomNavigationView` menu items

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
